package com.polluxframework.paginator.dialect;

import com.polluxframework.paginator.constant.DialectEnum;

/**
 * @author zhumin0508
 * created in  2018/9/28 15:20
 * modified By:
 */
public class DialectFactory {

	private static final MySqlDialect mySqlDialect = new MySqlDialect();

	public static Dialect buildDialect(DialectEnum dialectEnum) {
		switch (dialectEnum) {
			case MYSQL:
				return mySqlDialect;
			case ORACLE:
				return null;
			case DB2:
				return null;
			default:
				return mySqlDialect;
		}
	}
}
