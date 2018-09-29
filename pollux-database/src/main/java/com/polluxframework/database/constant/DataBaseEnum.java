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
	MYSQL,
	/**
	 * oracle
	 */
	ORACLE,
	/**
	 * DB2
	 */
	DB2;

	DataBaseEnum() {
	}

	public String getLowCase(){
		return this.name().toLowerCase();
	}
}
