package com.polluxframework.entity;

/**
 * @author zhumin0508
 * created in  2018/11/9 14:51
 * modified By:
 */
public class Model {
	/**
	 *实体类名称
	 */
	private String value;
	/**
	 * 实体类描述
	 */
	private String description;

	/**
	 * 实体类父类
	 */
	private Class<?> parent;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Class<?> getParent() {
		return parent;
	}

	public void setParent(Class<?> parent) {
		this.parent = parent;
	}
}
