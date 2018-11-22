package com.polluxframework.common.entity;

/**
 * @author zhumin0508
 * created in  2018/11/9 14:51
 * modified By:
 */
public class ModelProperty {

	private String value;

	private String name;

	private String notes;

	private String dataType;

	private boolean required;

	private boolean readOnly;

	private boolean hidden;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ModelProperty{");
		sb.append("value='").append(value).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", notes='").append(notes).append('\'');
		sb.append(", dataType='").append(dataType).append('\'');
		sb.append(", required=").append(required);
		sb.append(", readOnly=").append(readOnly);
		sb.append(", hidden=").append(hidden);
		sb.append('}');
		return sb.toString();
	}
}
