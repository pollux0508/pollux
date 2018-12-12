package com.polluxframework.swagger.entity;

/**
 * @author zhumin0508
 * created in  2018/11/26 16:16
 * modified By:
 */
public class Response {
	private String name;

	private String value;

	private String dataType;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
