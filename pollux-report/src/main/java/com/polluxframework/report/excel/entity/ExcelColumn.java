package com.polluxframework.report.excel.entity;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/6 16:40
 * modified By:
 */
public class ExcelColumn {
	/**
	 * 第几行，这个值只在内部使用，外部隐藏
	 */
	private int rowNum;

	/**
	 * 第几列，这个值只在内部使用，外部隐藏
	 */
	private int cosNum;
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
	private short align = HSSFCellStyle.ALIGN_CENTER;
	/**
	 * 表头对齐格式 'left'，'right'，'center' 默认居中
	 */
	private short halign = HSSFCellStyle.ALIGN_CENTER;
	/**
	 * 数据横向对齐格式 'top', 'middle', 'bottom' 默认居中
	 */
	private short valign = HSSFCellStyle.VERTICAL_CENTER;

	/**
	 * 获取不到值时的默认值
	 */
	private String defaultValue="";

	/**
	 * 孩子节点，主要用于复杂表头使用
	 */
	private List<ExcelColumn> children;


	public ExcelColumn() {
	}

	public ExcelColumn(ExcelColumn column) {
		this.rowNum = column.getRowNum();
		this.cosNum = column.getCosNum();
		this.name = column.getName();
		this.field = column.getField();
		this.align = column.getAlign();
		this.valign = column.getValign();
		this.display = column.isDisplay();
		this.colspan = column.getColspan();
		this.rowspan = column.getRowspan();
		this.halign = column.getHalign();
	}

	public int getRowNum() {
		return rowNum;
	}

	protected void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getCosNum() {
		return cosNum;
	}

	protected void setCosNum(int cosNum) {
		this.cosNum = cosNum;
	}

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

	public short getAlign() {
		return align;
	}

	public void setAlign(short align) {
		this.align = align;
	}

	public short getHalign() {
		return halign;
	}

	public void setHalign(short halign) {
		this.halign = halign;
	}

	public short getValign() {
		return valign;
	}

	public void setValign(short valign) {
		this.valign = valign;
	}

	public List<ExcelColumn> getChildren() {
		return children;
	}

	public void setChildren(List<ExcelColumn> children) {
		this.children = children;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void init() {
		if (this.children != null) {
			int size = this.children.size();
			int prev = 0;
			for (ExcelColumn column : this.children) {
				column.setRowNum(this.rowNum + this.rowspan);
				column.setCosNum(this.cosNum + prev);
				prev += column.getColspan();
				column.init();
			}
		}
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("rowNum:").append(rowNum);
		sb.append(", cosNum:").append(cosNum);
		sb.append(", name:'").append(name).append('\'');
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
