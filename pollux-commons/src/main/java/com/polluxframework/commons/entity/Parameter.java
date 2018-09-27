package com.polluxframework.commons.entity;

/**
 * @author zhumin0508
 * created in  2018/9/15 11:24
 * modified By:
 */
public class Parameter {
	private String name;
	private String type;
	private String description;
	private Boolean required;

	public Parameter() {
	}

	public Parameter(String name, String type) {
		this(name, type, "");
	}

	public Parameter(String name, String type, String description) {
		this(name, type, description, true);
	}

	public Parameter(String name, String type, String description, Boolean required) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.required = required;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Parameter{");
		sb.append("name='").append(name).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", required=").append(required);
		sb.append('}');
		return sb.toString();
	}
}
