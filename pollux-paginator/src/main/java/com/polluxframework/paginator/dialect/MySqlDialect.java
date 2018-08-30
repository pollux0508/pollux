package com.polluxframework.paginator.dialect;

import com.polluxframework.paginator.entity.PageBounds;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * @author zhumin0508
 * created in  2018/8/30 10:18
 * modified By:
 */
public class MySqlDialect extends Dialect {

	public MySqlDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
		super(mappedStatement, parameterObject, pageBounds);
	}

	@Override
	protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
		StringBuffer buffer = new StringBuffer(sql.length() + 20).append(sql);
		if (offset > 0) {
			buffer.append(" limit ?, ?");
			setPageParameter(offsetName, offset, Integer.class);
			setPageParameter(limitName, limit, Integer.class);
		} else {
			buffer.append(" limit ?");
			setPageParameter(limitName, limit, Integer.class);
		}
		return buffer.toString();
	}

}
