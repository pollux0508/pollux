package com.polluxframework.report.excel.utils;

import com.polluxframework.report.excel.entity.ExcelColumn;
import com.polluxframework.report.excel.entity.ExcelHeader;
import com.polluxframework.report.excel.entity.ExcelTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/6 17:11
 * modified By:
 */
public class ExcelUtilsTest {

	public ExcelTable initTable() {
		ExcelTable table = new ExcelTable();
		table.setName("学员考试成绩");
		table.setColumns(new ArrayList<>());

		ExcelColumn first = new ExcelColumn();
		first.setRowspan(2);
		first.setName("测试成绩");
		first.setField("f0");
		table.getColumns().add(first);

		ExcelColumn column = new ExcelColumn();
		column.setRowspan(1);
		column.setColspan(5);
		column.setName("学员考试成绩一览表");
		column.setChildren(new ArrayList<>());
		for (int i = 0; i < 5; i++) {
			ExcelColumn child = new ExcelColumn();
			child.setName("姓名" + i);
			child.setField("f"+i);
			column.getChildren().add(child);
		}
		table.getColumns().add(column);

		ExcelColumn other = new ExcelColumn();
		other.setRowspan(2);
		other.setName("考试成绩");
		other.setField("f5");
		table.getColumns().add(other);
		table.init();
		return table;
	}

	@Test
	public void getExcelTableHeader() {
		ExcelTable table = initTable();
		System.out.println(table);
		ExcelHeader header = ExcelUtils.getExcelTableHeader(table);
		System.out.println(header);
	}

	@Test
	public void getHSSFWorkbook() throws IOException {
		ExcelTable table = initTable();
		List<TestData> datas = new ArrayList<>();
		for(int i=0;i<100;i++){
			String str=""+i;
			TestData temp = new TestData(str+"0",str+"1",str+"2",str+"3",str+"5",str+"6");
			datas.add(temp);
		}
		table.setData(datas);
		HSSFWorkbook hssfWorkbook = ExcelUtils.getHSSFWorkbook(table.getName(),table);
		FileOutputStream output=new FileOutputStream("d:\\workbook.xls");
		hssfWorkbook.write(output);
		output.flush();
		output.close();
	}

}

