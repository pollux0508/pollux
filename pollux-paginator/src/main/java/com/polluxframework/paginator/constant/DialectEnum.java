package com.polluxframework.paginator.constant;

/**
 * @author zhumin0508
 * created in  2018/9/28 15:10
 * modified By:
 */
public enum DialectEnum {
	/**
	 * MySQL
	 */
	MYSQL("mysql"),
	/**
	 * Oracle
	 */
	ORACLE("oracle"),
	/**
	 * DB2
	 */
	DB2("db2");

	private String type;

	DialectEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
