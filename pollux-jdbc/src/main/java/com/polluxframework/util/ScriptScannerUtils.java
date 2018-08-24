package com.polluxframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/16 16:12
 * modified By:
 */
public class ScriptScannerUtils {
	private static final Logger logger = LoggerFactory.getLogger(ScriptScannerUtils.class);

	private ScriptScannerUtils(){
	}

	public static Reader getReaderByTable(String module, String dbType,String sqlType){
		StringBuilder stringBuilder = new StringBuilder("com/polluxframework/db/");
		stringBuilder.append(sqlType).append('/');
		stringBuilder.append(module).append('.');
		stringBuilder.append(dbType).append('.');
		stringBuilder.append(sqlType).append(".sql");
		BufferedReader reader = new BufferedReader(new InputStreamReader(ScriptScannerUtils.class.getClassLoader().getResourceAsStream(stringBuilder.toString())));
		return reader;
	}

	public static List<String> readScript(Reader reader) throws IOException {
		List<String> sqlList = new ArrayList<>(16);
		StringBuffer command = null;
		LineNumberReader lineReader = new LineNumberReader(reader);
		String line = null;
		while ((line = lineReader.readLine()) != null) {
			if (command == null) {
				command = new StringBuffer();
			}
			String trimmedLine = line.trim();
			if (trimmedLine.startsWith("--")) {
				logger.info(trimmedLine);
			} else if (trimmedLine.length() >= 1 && !trimmedLine.startsWith("//") && trimmedLine.length() >= 1 && !trimmedLine.startsWith("--")) {
				if (trimmedLine.endsWith(";")) {
					command.append(line.substring(0, line.lastIndexOf(";")));
					command.append(" ");
					logger.info(command.toString());
					sqlList.add(command.toString());
					command = null;
				} else {
					command.append(line);
					command.append(" ");
				}
			}
		}
		return sqlList;
	}
}