package com.polluxframework.paginator.dialect;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * @author zhumin0508
 * created in  2018/8/30 10:18
 * modified By:
 */
public abstract class Dialect {
	private String pageSQL;
	private String countSQL;

	public void build(MappedStatement mappedStatement, Object parameterObject) {
		BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
		pageSQL = boundSql.getSql();
		countSQL = getCountString(pageSQL);
	}

	public String getPageSQL() {
		return pageSQL;
	}

	public String getCountSQL() {
		return countSQL;
	}

	/**
	 * 将sql变成分页sql语句
	 *
	 * @param sql 查询SQL
	 * @param offset 查询起始位置
	 * @param limit 每页条数
	 * @return 携带分页条件的SQL
	 */
	public abstract String getLimitString(String sql, int offset, int limit);

	/**
	 * 将sql转换为总记录数SQL
	 *
	 * @param sql SQL语句
	 * @return 总记录数的sql
	 */
	protected String getCountString(String sql) {
		return "select count(1) from (" + sql + ") tmp_count";
	}
}
