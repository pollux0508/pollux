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
        first.setRowspan(3);
        first.setColspan(2);
        first.setName("测试成绩1");
        first.setField("f0");
        first.setMerge("f0");
        table.getColumns().add(first);

        ExcelColumn column = new ExcelColumn();
        column.setRowspan(2);
        column.setColspan(6);
        column.setName("学员考试成绩一览表");
        column.setChildren(new ArrayList<>());
        for (int i = 0; i < 6; i++) {
            ExcelColumn child = new ExcelColumn();
            child.setName("姓名" + i);
            child.setField("f" + i);
            column.getChildren().add(child);
        }
        table.getColumns().add(column);

        ExcelColumn other = new ExcelColumn();
        other.setRowspan(3);
        other.setColspan(2);
        other.setName("考试成绩2");
        other.setField("f5");
        other.setMerge("f5");
        table.getColumns().add(other);

        column = new ExcelColumn();
        column.setRowspan(1);
        column.setColspan(7);
        column.setName("学员考试成绩一览表");
        column.setChildren(new ArrayList<>());
        for (int i = 0; i < 6; i++) {
            ExcelColumn child = new ExcelColumn();
            child.setName("曹操" + i);
            if (i == 0) {
                child.setColspan(2);
                child.setChildren(new ArrayList<>());
                ExcelColumn child1 = new ExcelColumn();
                child1.setName("刘备1");
                child1.setField("f1");
                child1.setMerge("f0");
                child.getChildren().add(child1);

                ExcelColumn child2 = new ExcelColumn();
                child2.setName("刘备2");
                child2.setField("f" + (i + 2));

                child.getChildren().add(child2);

            } else {
                child.setField("f" + (i + 1));
                child.setRowspan(2);
            }
            if (i == 5) {
                child.setField("f" + (i + 1));
                child.setDefaultValue("-");
            }
            column.getChildren().add(child);
        }

        table.getColumns().add(column);

        column = new ExcelColumn();
        column.setRowspan(3);
        column.setColspan(2);
        column.setName("测试成绩3");
        column.setField("f1");
        table.getColumns().add(column);
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
        for (int i = 0; i < 200; i++) {
            String str = "" + i;
            TestData temp = new TestData(i / 10 + "0", str + "1", str + "2", str + "3", str + "5", "6");
            datas.add(temp);
        }
        table.setData(datas);
        HSSFWorkbook hssfWorkbook = ExcelUtils.getHSSFWorkbook(table.getName(), table);
        FileOutputStream output = new FileOutputStream("d:\\workbook.xls");
        hssfWorkbook.write(output);
        output.flush();
        output.close();
    }

}

