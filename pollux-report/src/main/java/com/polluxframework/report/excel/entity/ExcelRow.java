package com.polluxframework.report.excel.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/7 13:43
 * modified By:
 */
public class ExcelRow {
	List<ExcelColumn> columns = new ArrayList<>();

	public List<ExcelColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ExcelColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("columns:").append(columns);
		sb.append('}');
		return sb.toString();
	}
}
