package com.polluxframework.util;

import com.polluxframework.entity.Version;
import com.polluxframework.scanner.HistoryVersion;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/16 16:11
 * modified By:
 */
public class ScriptScannerTest {

	@Test
	public void getReaderByTable() {
		List<String> list = new ArrayList<>();
		try {
			List<Version> versions = HistoryVersion.getVersions();
			String currentVersion = "1.0.0";
			boolean flag = false;
			for (Version version : versions) {
				if (version.matches(currentVersion)) {
					flag = true;
					continue;
				}
				if (flag) {
					list.addAll(ScriptScannerUtils.readScript(ScriptScannerUtils.getReaderByTable("jdbc", "mysql", "update", version.getMainVersion())));
				}
			}

			for (String bean : list) {
				System.out.println(bean);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}