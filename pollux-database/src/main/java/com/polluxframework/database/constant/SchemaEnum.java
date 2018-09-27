package com.polluxframework.database.constant;

/**
 * 升级模式
 *
 * @author zhumin0508
 * created in  2018/9/12 16:16
 * modified By:
 */

public enum SchemaEnum {
	/**
	 * 只执行创建表
	 */
	CREATE(0, "create"),
	/**
	 * 只执行更新
	 */
	UPDATE(1, "update"),
	/**
	 * 删除表
	 */
	DROP(2, "drop"),
	/**
	 * 删除表后建表
	 */
	DROP_CREATE(9, "drop_create");

	private int type;
	private String name;

	SchemaEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}
