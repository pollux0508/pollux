package com.polluxframework.report.excel.entity;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/6 16:55
 * modified By:
 */
public class ExcelTable<T> {
	private boolean hasInit = false;
	/**
	 * 表名标题
	 */
	private String name;
	/**
	 * 是否隔行换颜色
	 */
	private boolean striped = false;
	/**
	 * 对应的列
	 */
	private List<ExcelColumn> columns;
	/**
	 * 对应的数据
	 */
	private List<T> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStriped() {
		return striped;
	}

	public void setStriped(boolean striped) {
		this.striped = striped;
	}

	public List<ExcelColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ExcelColumn> columns) {
		this.columns = columns;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public void init() {
		if(hasInit){
			return;
		}
		int size = columns.size();
		int prev = 0;
		for (int i = 0; i < size; i++) {
			ExcelColumn column = columns.get(i);
			column.setCosNum(prev);
			column.init();
			prev += column.getColspan();
		}
	}

	public boolean isHasInit() {
		return hasInit;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("name:'").append(name).append('\'');
		sb.append(", striped:").append(striped);
		sb.append(", columns:").append(columns);
		sb.append(", data:").append(data);
		sb.append('}');
		return sb.toString();
	}
}
