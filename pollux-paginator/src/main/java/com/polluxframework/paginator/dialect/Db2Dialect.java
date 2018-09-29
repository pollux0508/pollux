package com.polluxframework.paginator.dialect;

/**
 * @author zhumin0508
 * created in  2018/9/29 8:33
 * modified By:
 */
public class Db2Dialect extends Dialect {
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		int endIndex = offset + limit;
		int startIndex = offset + 1;
		return "SELECT * FROM (SELECT row_number() over() AS _rn, _ROW.* FROM(" +
				sql + ") AS _ROW WHERE _rn <=" + endIndex + ")WHERE _rn >= " + startIndex;
	}
}
