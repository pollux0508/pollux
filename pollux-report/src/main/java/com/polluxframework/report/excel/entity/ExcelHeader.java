package com.polluxframework.report.excel.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * excel表格头部
 *
 * @author zhumin0508
 * created in  2018/9/7 13:32
 * modified By:
 */
public class ExcelHeader {

	private List<ExcelRow> rows = new ArrayList<>();

	public List<ExcelRow> getRows() {
		return rows;
	}

	public void setRows(List<ExcelRow> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("rows:").append(rows);
		sb.append('}');
		return sb.toString();
	}
}
