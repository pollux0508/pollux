package com.polluxframework.version.utils;

import com.polluxframework.version.constant.DataBaseEnum;
import com.polluxframework.version.constant.SchemaEnum;
import com.polluxframework.version.entity.IModuleVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/12 16:11
 * modified By:
 */
public class ScriptScannerUtils {
	private static final Logger logger = LoggerFactory.getLogger(ScriptScannerUtils.class);

	public static List<String> readScript(IModuleVersion moduleVersion, DataBaseEnum baseEnum, SchemaEnum schemaEnum, String updateVersion) throws IOException {
		StringBuilder stringBuilder = new StringBuilder(moduleVersion.getSQLDirectory());
		stringBuilder.append('/').append(schemaEnum.getName()).append('/');
		stringBuilder.append(moduleVersion.getModule()).append('.');
		stringBuilder.append(baseEnum.getLowCase()).append('.');
		stringBuilder.append(schemaEnum.getName());

		if (SchemaEnum.UPDATE.equals(schemaEnum)) {
			stringBuilder.append('.').append(updateVersion);
		}

		stringBuilder.append(".sql");
		Reader reader = new BufferedReader(new InputStreamReader(moduleVersion.getClass().getClassLoader().getResourceAsStream(stringBuilder.toString())));
		return readScript(reader);
	}


	public static List<String> readScript(Class cls, String file) throws IOException {
		Reader reader = new BufferedReader(new InputStreamReader(cls.getClassLoader().getResourceAsStream(file)));
		return readScript(reader);
	}

	public static List<String> readScript(Reader reader) throws IOException {
		List<String> sqlList = new ArrayList<>(16);
		StringBuffer command = new StringBuffer();
		LineNumberReader lineReader = new LineNumberReader(reader);
		String line;
		while ((line = lineReader.readLine()) != null) {
			String trimmedLine = line.trim();
			if (trimmedLine.startsWith("--") || trimmedLine.startsWith("//")) {
				logger.debug("SQL的注释:{}", trimmedLine);
			} else if (trimmedLine.length() >= 1) {
				if (trimmedLine.endsWith(";")) {
					line = line.substring(0, line.lastIndexOf(";"));
					command.append(line).append(" ");

					logger.debug("SQL:{}",command.toString());
					sqlList.add(command.toString());
					command = new StringBuffer();
				} else {
					command.append(line).append(" ");
				}
			}
		}
		return sqlList;
	}

}
