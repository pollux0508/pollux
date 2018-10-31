package com.polluxframework.database.util;

import com.polluxframework.database.constant.DataBaseEnum;
import com.polluxframework.database.constant.SchemaEnum;
import com.polluxframework.database.entity.IModuleVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/12 16:11
 * modified By:
 */
public class ScriptScannerUtils {

	private ScriptScannerUtils() {
	}

	private static final Logger logger = LoggerFactory.getLogger(ScriptScannerUtils.class);

	public static List<String> readScript(IModuleVersion moduleVersion, DataBaseEnum baseEnum, SchemaEnum schemaEnum, String updateVersion) throws IOException {
		StringBuilder stringBuilder = new StringBuilder(moduleVersion.getSQLDirectory());
		stringBuilder.append('/').append(schemaEnum.getName()).append('/');
		stringBuilder.append(moduleVersion.getModule()).append('.');
		stringBuilder.append(baseEnum.getLowCase()).append('.');
		stringBuilder.append(schemaEnum.getName());

		if (SchemaEnum.UPDATE == schemaEnum) {
			stringBuilder.append('.').append(updateVersion);
		}

		stringBuilder.append(".sql");
		return readScript(moduleVersion.getClass(), stringBuilder.toString());
	}


	public static List<String> readScript(Class cls, String file) throws IOException {
		Reader reader = new BufferedReader(new InputStreamReader(cls.getClassLoader().getResourceAsStream(file), StandardCharsets.UTF_8));
		return readScript(reader);
	}

	public static List<String> readScript(Reader reader) throws IOException {
		if(reader==null){
			throw new IllegalStateException("无法获取脚本文件");
		}
		List<String> sqlList = new ArrayList<>(16);
		StringBuilder command = new StringBuilder();
		LineNumberReader lineReader = new LineNumberReader(reader);
		String line;
		while ((line = lineReader.readLine()) != null) {
			String trimmedLine = line.trim();
			if (trimmedLine.startsWith("--") || trimmedLine.startsWith("//")) {
				trimmedLine = trimmedLine.replaceAll("[\r\n]", "");
				logger.debug("SQL的注释:{}", trimmedLine);
			} else if (trimmedLine.length() >= 1) {
				if (trimmedLine.endsWith(";")) {
					line = line.substring(0, line.lastIndexOf(';'));
					command.append(line).append(' ');
					String sql = command.toString();
					sql = sql.replaceAll("[\r\n]", "");
					logger.debug("SQL:{}", sql);
					sqlList.add(sql);
					command = new StringBuilder();
				} else {
					command.append(line).append(' ');
				}
			}
		}
		return sqlList;
	}

}
