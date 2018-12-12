package com.polluxframework.swagger.entity;

/**
 * @author zhumin0508
 * created in  2018/11/26 16:09
 * modified By:
 */
public class ApiParameter {

	private String name;

	private String value;

	private String dataType;

	private String example;

	private boolean required;

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

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
}
