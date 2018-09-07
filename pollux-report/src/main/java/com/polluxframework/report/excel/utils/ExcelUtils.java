package com.polluxframework.report.excel.utils;

import com.polluxframework.commons.utils.ReflectionUtils;
import com.polluxframework.report.excel.entity.ExcelColumn;
import com.polluxframework.report.excel.entity.ExcelHeader;
import com.polluxframework.report.excel.entity.ExcelRow;
import com.polluxframework.report.excel.entity.ExcelTable;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhumin0508
 * created in  2018/9/6 17:02
 * modified By:
 */
public class ExcelUtils {
	private static final int MAX_ROWS = 65535;
	private static final int MAX_COLUMN = 255;

	private ExcelUtils() {
	}

	public static HSSFWorkbook getHSSFWorkbook(String sheetName, ExcelTable<T> table) {
		table.init();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
		HSSFCellStyle style = getStandStyle(hssfWorkbook);
		ExcelHeader header = getExcelTableHeader(table);
		int rownum = header.getRows().size();
		for (int i = 0; i < rownum; i++) {
			sheet.createRow(i);
		}
		for (int i = 0; i < rownum; i++) {
			HSSFRow row = sheet.getRow(i);
			List<ExcelColumn> columns = header.getRows().get(i).getColumns();
			int columnSize = columns.size();
			for (int j = 0; j < columnSize; j++) {
				ExcelColumn column = columns.get(j);
				HSSFCell cell = row.createCell(column.getCosNum());
				cell.setCellValue(column.getName());
				HSSFCellStyle styles = hssfWorkbook.createCellStyle();
				styles.cloneStyleFrom(style);
				styles.setAlignment(column.getAlign());
				styles.setVerticalAlignment(column.getValign());
				cell.setCellStyle(styles);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);

				if (column.getRowspan() > 1 || column.getColspan() > 1) {
					CellRangeAddress cellRangePlanNo =new CellRangeAddress(i, i + column.getRowspan() - 1, column.getCosNum(), column.getCosNum() + column.getColspan() - 1);
					sheet.addMergedRegion(cellRangePlanNo);
					setBorderForMergeCell(HSSFCellStyle.BORDER_THIN,cellRangePlanNo,sheet,hssfWorkbook);
				}
				sheet.autoSizeColumn(column.getCosNum(), true);
			}
		}
		List<ExcelColumn> real = new ArrayList<>();
		realColumn(real, table.getColumns());
		int initNum = rownum;
		for (Object data : table.getData()) {
			HSSFRow row = sheet.createRow(initNum++);
			for (int i = 0; i < real.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(ReflectionUtils.getFieldValueByName(data,real.get(i).getField(),"").toString());
				style.setAlignment(real.get(i).getAlign());
				style.setVerticalAlignment(real.get(i).getValign());
				cell.setCellStyle(style);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			}
		}
		return hssfWorkbook;
	}

	public static HSSFCellStyle getStandStyle(HSSFWorkbook hssfWorkbook) {
		HSSFCellStyle style = hssfWorkbook.createCellStyle();
		HSSFFont font = hssfWorkbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return style;
	}

	private static void setBorderForMergeCell(int i, CellRangeAddress cellRangeTitle, Sheet sheet, HSSFWorkbook workBook){
		RegionUtil.setBorderBottom(i, cellRangeTitle, sheet, workBook);
		RegionUtil.setBorderLeft(i, cellRangeTitle, sheet, workBook);
		RegionUtil.setBorderRight(i, cellRangeTitle, sheet, workBook);
		RegionUtil.setBorderTop(i, cellRangeTitle, sheet, workBook);
	}
	public static ExcelHeader getExcelTableHeader(ExcelTable<T> table) {
		table.init();
		List<ExcelColumn> columns = table.getColumns();
		ExcelHeader excelHeader = new ExcelHeader();
		initHeader(excelHeader, table.getColumns().get(0));
		initRow(excelHeader.getRows(), table.getColumns());
		return excelHeader;
	}

	private static void initHeader(ExcelHeader excelHeader, ExcelColumn column) {
		for (int i = 0; i < column.getRowspan(); i++) {
			ExcelRow row = new ExcelRow();
			excelHeader.getRows().add(row);
			if (column.getChildren() != null && column.getChildren().size() > 0) {
				initHeader(excelHeader, column.getChildren().get(0));
			}
		}
	}

	private static void initRow(List<ExcelRow> rows, List<ExcelColumn> columns) {
		int size = columns.size();
		for (int i = 0; i < size; i++) {
			ExcelColumn bean = columns.get(i);
			ExcelColumn temp = new ExcelColumn(bean);
			rows.get(temp.getRowNum()).getColumns().add(temp);
			if (bean.getChildren() != null) {
				initRow(rows, bean.getChildren());
			}
		}
	}

	private static void realColumn(List<ExcelColumn> columns, List<ExcelColumn> tableColumn) {
		for (ExcelColumn column : tableColumn) {
			if (column.getChildren()!=null&&(!column.getChildren().isEmpty())) {
				realColumn(columns, column.getChildren());
			} else {
				columns.add(column);
			}
		}
	}
}
