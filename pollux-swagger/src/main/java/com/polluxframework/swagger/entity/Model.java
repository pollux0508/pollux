package com.polluxframework.swagger.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/11/9 14:51
 * modified By:
 */
public class Model {
	/**
	 * 实体类名称
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

	private String cls;


	List<ModelProperty> fields = new ArrayList<>(16);

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

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public List<ModelProperty> getFields() {
		return fields;
	}

	public void setFields(List<ModelProperty> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Model{");
		sb.append("value='").append(value).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", cls='").append(cls).append('\'');
		sb.append(", fields=").append(fields);
		sb.append('}');
		return sb.toString();
	}
}
