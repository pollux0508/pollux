package com.polluxframework.paginator.dialect;

/**
 * @author zhumin0508
 * created in  2018/8/30 10:18
 * modified By:
 */
public class MySqlDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		StringBuilder buffer = new StringBuilder(sql.length() + 20).append(sql);
		buffer.append(" limit");
		if (offset > 0) {
			buffer.append(' ').append(offset).append(',');
		}
		buffer.append(' ').append(limit);
		return buffer.toString();
	}
}
