package com.polluxframework.paginator.support;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author zhumin0508
 * created in  2018/8/30 10:24
 * modified By:
 */
public class BoundSqlSqlSource implements SqlSource {
	BoundSql boundSql;

	public BoundSqlSqlSource(BoundSql boundSql){
		this.boundSql = boundSql;
	}
	@Override
	public BoundSql getBoundSql(Object parameterObject) {
		return boundSql;
	}
}
