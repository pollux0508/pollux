package com.polluxframework.paginator;

import com.polluxframework.paginator.constant.DialectEnum;
import com.polluxframework.paginator.dialect.Dialect;
import com.polluxframework.paginator.dialect.DialectFactory;
import com.polluxframework.paginator.entity.PageBounds;
import com.polluxframework.paginator.support.SqlHelp;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

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
	private static final int MAPPED_STATEMENT_INDEX = 0;
	private static final int PARAMETER_INDEX = 1;
	private static final int ROW_BOUNDS_INDEX = 2;

	protected DialectEnum dialectEnum = DialectEnum.MYSQL;

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
			return invocation.proceed();
		}

		final Dialect dialect = DialectFactory.buildDialect(dialectEnum);

		assert dialect != null;

		dialect.build(mappedStatement, parameter);

		int count;
		String pageSql = dialect.getPageSQL();
		if (!pageBounds.getForceNoCount()) {
			Callable<Integer> countTask = () -> SqlHelp.getCount(mappedStatement, executor.getTransaction(), parameter, mappedStatement.getBoundSql(parameter), dialect);
			Future<Integer> futureCount = call(countTask);
			count = futureCount.get();
			if (count < pageBounds.getOffset()) {
				int pageNo = count < pageBounds.getLimit() ? 1 : count % pageBounds.getLimit() == 0 ? count / pageBounds.getLimit() : count / pageBounds.getLimit() + 1;
				pageSql = dialect.getLimitString(pageSql, (pageNo - 1) * pageBounds.getLimit(), pageBounds.getLimit());
			} else {
				pageSql = dialect.getLimitString(pageSql, pageBounds.getOffset(), pageBounds.getLimit());
			}
		}
		queryArgs[MAPPED_STATEMENT_INDEX] = buildMappedStatement(mappedStatement, pageSql, parameter);
		queryArgs[ROW_BOUNDS_INDEX] = new RowBounds();
		Future<List> listFuture = call(() -> (List) invocation.proceed());
		List list = listFuture.get();
		return list;
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
		String property = properties.getProperty("dialect");
		if (property == null) {
			return;
		}
		if (DialectEnum.MYSQL.equals(property.toLowerCase())) {
			dialectEnum = DialectEnum.MYSQL;
		}
	}
}
