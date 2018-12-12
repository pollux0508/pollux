package com.polluxframework.common.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhumin0508
 * created in  2018/9/15 9:22
 * modified By:
 */
public enum ClassEnum {
	/**
	 * 字符串
	 */
	STRING("String", "Strings"),
	/**
	 * 长整型
	 */
	LONG("Long", "long"),
	/**
	 * 双精度
	 */
	DOUBLE("Double", "double"),
	/**
	 * 单精度
	 */
	FLOAT("Float", "float"),
	/**
	 * 字符
	 */
	CHAR("Character", "char"),
	/**
	 * 布尔
	 */
	BOOLEAN("Boolean", "boolean"),
	/**
	 * 位
	 */
	BYTE("Byte","byte"),
	/**
	 * 整型
	 */
	INT("Integer","int"),
	/**
	 * 短整型
	 */
	SHORT("Short","short");


	private String boxName;
	private String name;

	ClassEnum(String boxName, String name) {
		this.boxName = boxName;
		this.name = name;
	}

	public String getBoxName() {
		return boxName;
	}

	public String getName() {
		return name;
	}

	public static Set<String> getAllBaseClass(){
		Set<String> result = new HashSet<>(32);
		result.add(STRING.getName());
		result.add(LONG.getBoxName());
		result.add(LONG.getName());
		result.add(DOUBLE.getBoxName());
		result.add(DOUBLE.getName());
		result.add(FLOAT.getBoxName());
		result.add(FLOAT.getName());
		result.add(CHAR.getBoxName());
		result.add(CHAR.getName());
		result.add(BOOLEAN.getBoxName());
		result.add(BOOLEAN.getName());
		result.add(BYTE.getBoxName());
		result.add(BYTE.getName());
		result.add(INT.getBoxName());
		result.add(INT.getName());
		result.add(SHORT.getBoxName());
		result.add(SHORT.getName());
		return result;
	}
}
