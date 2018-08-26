package com.polluxframework.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhumin0508
 * created in  2018/8/8 16:04
 * modified By:
 */
public class Index {
	private static final String PRIMARY = "PRIMARY";
	private String table;
	private String column;
	private String name;
	private int seq;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.isNotEmpty(name) && PRIMARY.equals(name) ? "" : name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
}
