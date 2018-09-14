package com.polluxframework.paginator;

import com.polluxframework.paginator.dialect.Dialect;
import com.polluxframework.paginator.entity.PageBounds;
import com.polluxframework.paginator.entity.PageModel;
import com.polluxframework.paginator.support.BoundSqlSqlSource;
import com.polluxframework.paginator.support.SqlHelp;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhumin0508
 * created in  2018/8/30 9:54
 * modified By:
 */
@Intercepts({@Signature(
		type = Executor.class,
		method = "query",
		args = {MappedStatement.class, RowBounds.class, Object.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
	private static final int MAPPED_STATEMENT_INDEX = 0;
	private static final int ROW_BOUNDS_INDEX = 1;
	private static final int PARAMETER_INDEX = 2;

	private String dialectClass;

	@Override
	public Object intercept(final Invocation invocation) throws Throwable {
		final Executor executor = (Executor) invocation.getTarget();
		final Object[] queryArgs = invocation.getArgs();
		final MappedStatement mappedStatement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		final Object parameter = queryArgs[PARAMETER_INDEX];
		final RowBounds rowBounds = (RowBounds) queryArgs[ROW_BOUNDS_INDEX];
		final PageBounds pageBounds = new PageBounds(rowBounds);
		if (pageBounds.isNoRow()) {
			return invocation.proceed();
		}
		final Dialect dialect;
		try {
			Class clazz = Class.forName(dialectClass);
			Constructor constructor = clazz.getConstructor(MappedStatement.class, Object.class, PageBounds.class);
			dialect = (Dialect) constructor.newInstance(new Object[]{mappedStatement, parameter, pageBounds});
		} catch (Exception e) {
			throw new ClassNotFoundException("Cannot create dialect instance: " + dialectClass, e);
		}
		final BoundSql boundSql = mappedStatement.getBoundSql(parameter);

		int count = 0;
		int pageNo = pageBounds.getPageNo();
		String pageSql = dialect.getPageSQL();

		if (pageBounds.getForceNoCount()) {
			Callable<Integer> countTask = new Callable() {
				@Override
				public Object call() throws Exception {
					return SqlHelp.getCount(mappedStatement, executor.getTransaction(), parameter, boundSql, dialect);
				}
			};
			Future<Integer> futureCount = call(countTask);
			count = futureCount.get();
			if (count < pageBounds.getOffset()) {
				if (count % pageBounds.getLimit() == 0) {
					pageNo = count / pageBounds.getLimit();
				} else {
					pageNo = count / pageBounds.getLimit() + 1;
				}
				if (pageNo < 1) {
					pageNo = 1;
				}
				logger.info("由于数据总数小于分页的起始值,默认取最大页:{}", pageNo);
				pageSql = dialect.getLimitString(pageSql, (pageNo - 1) * pageBounds.getLimit(), pageBounds.getLimit());
			} else {
				pageSql = dialect.getLimitString(pageSql, pageBounds.getOffset(), pageBounds.getLimit());
			}
		}

		queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(mappedStatement, boundSql, pageSql, dialect.getParameterMappings(), dialect.getParameterObject());
		queryArgs[PARAMETER_INDEX] = dialect.getParameterObject();
		queryArgs[ROW_BOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
		Future<List> listFuture = call(new Callable<List>() {
			@Override
			public List call() throws Exception {
				return (List) invocation.proceed();
			}
		});
		List list = listFuture.get();

		PageModel pageModel = new PageModel();
		pageModel.addAll(list);
		if (pageBounds.isContainsTotalCount()) {
			pageModel.setTotal(count);
			pageModel.setPageSize(pageBounds.getLimit());
			pageModel.setPageNo(pageNo);
		}
		return pageModel;
	}

	private <T> Future<T> call(Callable callable) {
		FutureTask<T> future = new FutureTask(callable);
		future.run();
		return future;
	}

	private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql, String sql, List<ParameterMapping> parameterMappings, Object parameter) {
		BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql, parameterMappings, parameter);
		return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
	}

	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql, List<ParameterMapping> parameterMappings, Object parameter) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, parameterMappings, parameter);
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
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

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		String property = properties.getProperty("dialectClass");

		if (isBlankString(property)) {
			property = "com.polluxframework.paginator.dialect.MySqlDialect";
		}
		logger.debug("dialectClass: {} ", property);
		this.dialectClass = property;
	}

	private static boolean isBlankString(String value) {
		return value == null || "".equals(value.trim());
	}
}
