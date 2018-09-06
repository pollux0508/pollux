package com.polluxframework.report.excel.utils;

import com.polluxframework.report.excel.entity.Column;
import com.polluxframework.report.excel.entity.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/6 17:02
 * modified By:
 */
public class ExcelUtils {

	private ExcelUtils() {
	}

	public static Table getTables() {
		Table<Object> table = new Table<>();
		table.setName("就是测试一下表格");
		List<Column> columns = new ArrayList<>();
		table.setColumns(columns);
		for (int i = 0; i < 5; i++) {
			Column column = new Column();
			columns.add(column);
			column.setName("中文" + i);
			column.setField("x" + i);
			column.setRowspan(2);
			if (i == 3) {
				column.setChildren(new ArrayList<>());
				column.setField(null);
				column.setRowspan(1);
				column.setColspan(3);
				for (int j = 0; j < 3; j++) {
					Column bean = new Column();
					column.getChildren().add(bean);
					bean.setName("中文" + i+j);
					bean.setField("x" + i+j);
					bean.setRowspan(1);
				}
			}
		}
		return table;
	}
}
