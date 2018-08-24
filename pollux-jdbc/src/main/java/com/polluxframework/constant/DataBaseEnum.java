package com.polluxframework.constant;

/**
 * @author zhumin0508
 * created in  2018/8/13 10:23
 * modified By:
 */
public enum DataBaseEnum {
	//Mysql
	MYSQL(0, "MYSQL"),
	//oracle
	ORACLE(1, "ORACLE"),
	//DB2
	DB2(2, "DB2");
	/**
	 * 数据库类型
	 */
	private Integer type;
	private String name;

	DataBaseEnum(Integer type, String name) {
		this.type = type;
		this.name = name;
	}

	public static Integer getTypeByName(String name){
		if(MYSQL.name.equals(name)){
			return MYSQL.type;
		}else if(ORACLE.name.equals(name)){
			return ORACLE.type;
		}else if(DB2.name.equals(name)){
			return DB2.type;
		}else{
			return MYSQL.type;
		}
	}
}
