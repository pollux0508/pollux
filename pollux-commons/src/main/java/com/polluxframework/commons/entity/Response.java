package com.polluxframework.commons.entity;

/**
 * @author zhumin0508
 * created in  2018/9/15 11:24
 * modified By:
 */
public class Response extends Parameter {


	public Response() {
	}

	public Response(Parameter parameter) {
		this(parameter.getName(), parameter.getType(), parameter.getDescription(), parameter.getRequired());
	}

	public Response(String name, String type, String description, Boolean required) {
		super(name, type, description, required);
	}

	public String getFullName(String prev) {
		return prev + super.getName();
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Response{");
		sb.append("name='").append(getName()).append('\'');
		sb.append(", type='").append(getType()).append('\'');
		sb.append(", description='").append(getDescription()).append('\'');
		sb.append(", required=").append(getRequired());
		sb.append('}');
		return sb.toString();
	}
}
