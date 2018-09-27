package com.polluxframework.database.constant;

/**
 * @author zhumin0508
 * created in  2018/8/13 10:23
 * modified By:
 */
public enum DataBaseEnum {
	/**
	 * Mysql
	 */
	MYSQL("MySQL", "mysql"),
	/**
	 * oracle
	 */
	ORACLE("ORACLE", "oracle"),
	/**
	 * DB2
	 */
	DB2("DB2", "db2");
	/**
	 * 数据库类型
	 */
	private String name;
	/**
	 * 数据库名称小写
	 */
	private String lowCase;

	DataBaseEnum(String name, String lowCase) {
		this.name = name;
		this.lowCase = lowCase;
	}

	public String getName() {
		return name;
	}

	public String getLowCase() {
		return lowCase;
	}

	public boolean match(String dbType) {
		return this.name.equals(dbType);
	}
}
