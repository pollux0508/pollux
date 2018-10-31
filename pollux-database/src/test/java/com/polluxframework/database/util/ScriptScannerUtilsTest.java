package com.polluxframework.database.util;

import com.polluxframework.database.constant.DataBaseEnum;
import com.polluxframework.database.constant.SchemaEnum;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/12 16:31
 * modified By:
 */
public class ScriptScannerUtilsTest {

	@Test
	public void readScript() throws IOException {
		DataBaseVersion version = new DataBaseVersion();
		List<String> sqlList = ScriptScannerUtils.readScript(version, DataBaseEnum.MYSQL, SchemaEnum.CREATE, "");
		for (String str : sqlList) {
			System.out.println(str);
		}
		String prev = "1.0.0";
		boolean flag = false;
		sqlList = new ArrayList<>();
		for (String bean : version.getHistoryVersion()) {
			if (prev.equals(bean)) {
				flag = true;
				continue;
			}
			if (flag) {
				sqlList.addAll(ScriptScannerUtils.readScript(version, DataBaseEnum.MYSQL, SchemaEnum.UPDATE, bean));
			}
		}
		for (String str : sqlList) {
			System.out.println(str);
		}
	}
}