package com.polluxframework.report.excel.entity;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/6 16:40
 * modified By:
 */
public class Column {
	/**
	 * 显示的名字
	 */
	private String name;
	/**
	 * 对应的字段
	 */
	private String field;
	/**
	 * 是否显示 默认显示
	 */
	private boolean display = true;
	/**
	 * 单元格应占用的行数 默认1行
	 */
	private int rowspan = 1;
	/**
	 * 单元格应占用的列数 默认1列
	 */
	private int colspan = 1;
	/**
	 * 数据对齐格式 'left'，'right'，'center' 默认居中
	 */
	private String align = "center";
	/**
	 * 表头对齐格式 'left'，'right'，'center' 默认居中
	 */
	private String halign = "center";
	/**
	 * 数据横向对齐格式 'top', 'middle', 'bottom' 默认居中
	 */
	private String valign = "middle";
	/**
	 * 孩子节点，主要用于复杂表头使用
	 */
	private List<Column> children;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getHalign() {
		return halign;
	}

	public void setHalign(String halign) {
		this.halign = halign;
	}

	public String getValign() {
		return valign;
	}

	public void setValign(String valign) {
		this.valign = valign;
	}

	public List<Column> getChildren() {
		return children;
	}

	public void setChildren(List<Column> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("name:'").append(name).append('\'');
		sb.append(", field:'").append(field).append('\'');
		sb.append(", display:").append(display);
		sb.append(", rowspan:").append(rowspan);
		sb.append(", colspan:").append(colspan);
		sb.append(", align:'").append(align).append('\'');
		sb.append(", halign:'").append(halign).append('\'');
		sb.append(", valign:'").append(valign).append('\'');
		sb.append(", children:").append(children);
		sb.append('}');
		return sb.toString();
	}
}
