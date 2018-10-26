package com.polluxframework.paginator;

import com.polluxframework.paginator.constant.DialectEnum;
import com.polluxframework.paginator.dialect.Dialect;
import com.polluxframework.paginator.entity.PageBounds;
import com.polluxframework.paginator.entity.PageList;
import com.polluxframework.paginator.support.DialectFactory;
import com.polluxframework.paginator.support.SqlHelp;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhumin0508
 * created in  2018/9/28 9:17
 * modified By:
 */
@Intercepts({@Signature(
		type = Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(PageInterceptor.class);

	private static final int MAPPED_STATEMENT_INDEX = 0;
	private static final int PARAMETER_INDEX = 1;
	private static final int ROW_BOUNDS_INDEX = 2;

	protected DialectEnum dialectEnum = DialectFactory.defaultDialectType();

	protected boolean autoDriver = true;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Executor executor = (Executor) invocation.getTarget();
		final Object[] queryArgs = invocation.getArgs();
		final MappedStatement mappedStatement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		final Object parameter = queryArgs[PARAMETER_INDEX];
		final Object rowBounds = queryArgs[ROW_BOUNDS_INDEX];

		final PageBounds pageBounds;
		if (rowBounds instanceof PageBounds) {
			pageBounds = (PageBounds) rowBounds;
		} else {
			pageBounds = null;
		}

		if (pageBounds == null || pageBounds.isNoRow()) {
			logger.debug("不需要使用分页查询");
			return invocation.proceed();
		}
		if (autoDriver) {
			dialectEnum = getDbType(executor);
		}
		final Dialect dialect = DialectFactory.buildDialect(dialectEnum);
		dialect.build(mappedStatement, parameter);

		int count = 0;
		int pageNo = 0;
		int offset = pageBounds.getOffset();
		String pageSql = dialect.getPageSQL();
		if (!pageBounds.getForceNoCount()) {
			Callable<Integer> countTask = () -> SqlHelp.getCount(mappedStatement, executor.getTransaction(), parameter, mappedStatement.getBoundSql(parameter), dialect);
			Future<Integer> futureCount = call(countTask);
			count = futureCount.get();
			pageNo = getCurPageNo(count, pageBounds);
			offset = (pageNo - 1) * pageBounds.getLimit();
		}
		pageSql = dialect.getLimitString(pageSql, offset, pageBounds.getLimit());

		PageList result;
		if (count > 0) {
			queryArgs[MAPPED_STATEMENT_INDEX] = buildMappedStatement(mappedStatement, pageSql, parameter);
			queryArgs[ROW_BOUNDS_INDEX] = new RowBounds();
			Future<List> listFuture = call(() -> (List) invocation.proceed());
			result = new PageList(listFuture.get());
		} else {
			result = new PageList();
		}

		if (pageBounds.isContainsTotalCount()) {
			result.setTotal(count);
			result.setPageNo(pageNo);
			result.setPageSize(pageBounds.getLimit());
		}
		return result;
	}

	private int getCurPageNo(int count, PageBounds pageBounds) {
		if (count < pageBounds.getOffset()) {
			int limit = pageBounds.getLimit();
			int pageNo = count % limit == 0 ? count / limit : count / limit + 1;
			pageNo = pageNo < 1 ? 1 : pageNo;
			logger.info("查询的起始位置大于数据总数，调整当前页面为：{}", pageNo);
			return pageNo;
		} else {
			return pageBounds.getPageNo();
		}
	}

	private DialectEnum getDbType(Executor executor) throws SQLException {
		Connection connection = executor.getTransaction().getConnection();
		DatabaseMetaData meta = connection.getMetaData();
		String dbType = meta.getDatabaseProductName();
		return DialectFactory.getDialectType(dbType);
	}


	private Object buildMappedStatement(MappedStatement mappedStatement, String pageSql, Object parameter) {
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		return buildMappedStatement(mappedStatement, new StaticSqlSource(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings()));
	}

	private MappedStatement buildMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
			StringBuilder keyProperties = new StringBuilder();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(',');
			}
			keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}

		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	private <T> Future<T> call(Callable<T> callable) {
		FutureTask<T> future = new FutureTask<>(callable);
		future.run();
		return future;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		String dialect = properties.getProperty("dialect");
		if (dialect == null) {
			this.autoDriver = true;
			logger.info("没有配置方言类型则采用自动适配驱动");
			return;
		}
		this.dialectEnum = DialectFactory.getDialectType(dialect);

		this.autoDriver = Boolean.parseBoolean(properties.getProperty("autoDriver"));
	}
}
