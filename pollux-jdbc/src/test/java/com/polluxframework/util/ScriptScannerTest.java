package com.polluxframework.util;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/16 16:11
 * modified By:
 */
public class ScriptScannerTest {

	@Test
	public void getReaderByTable() {
		try {
			List<String> list= ScriptScannerUtils.readScript(ScriptScannerUtils.getReaderByTable("jdbc","mysql","create"));
			for(String bean:list){
				System.out.println(bean);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}