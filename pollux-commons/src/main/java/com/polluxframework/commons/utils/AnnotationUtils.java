package com.polluxframework.commons.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * 获取类中的注释信息
 *
 * @author zhumin0508
 * created in  2018/9/15 8:09
 * modified By:
 */
public class AnnotationUtils {
	private static Pattern pattern = compile("/\\*.*?\\*/", DOTALL);

	public static void getMethodAnnotation(File file) throws IOException {
		if (!file.exists()) {
			return;
		}
		String str = replaceBlank(FileUtils.readFileToStr(file, "UTF-8"));
		List<String> api = new ArrayList<>();
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			api.add(str.substring(m.start(), m.end()));
		}
	}

	private static String getControllerMapping(String str){
		String controller="";
		Pattern p = compile("(@Controller.*?@RequestMapping\\(\"/.*?\"\\))", DOTALL);
		Matcher m = p.matcher(str);
		if (m.find()) {
			String temp = str.substring(m.start(), m.end());
			p = compile("(@RequestMapping\\(\"/.*?\"\\))", DOTALL);
			m = p.matcher(str);
			if (m.find()) {
				controller = str.substring(m.start() + 18, m.end() - 2);
			}
			str = str.substring(m.end());
		}
		return controller;
	}

	private static String replaceBlank(String str) {
		str = str.replaceAll("\r\n", "");
		str = str.replaceAll("\t", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll(" ", "");
		return str;
	}
}
