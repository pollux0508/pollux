package com.polluxframework.commons.utils;

import org.reflections.Reflections;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhumin0508
 * created in  2018/9/18 9:07
 * modified By:
 */
public class ApiUtils {
	private static final String FILE_STR = "file";

	public static Set<Class<?>> scanPackageController(String packageName, boolean subPackage) {
		Set<String> packages = new HashSet<>(8);
		packages.add(packageName);
		if (subPackage) {
			packages.addAll(getPackageList(packageName));
		}
		Set<Class<?>> classes = new HashSet<>(16);
		for (String bean : packages) {
			Reflections reflections = new Reflections(packageName);
			classes.addAll(reflections.getTypesAnnotatedWith(Controller.class));
		}
		return classes;
	}

	public static Set<Class<?>> scanPackageController(String packageName) {
		return scanPackageController(packageName, false);
	}

	public static Set<String> getPackageList(String packageName) {
		Set<String> fileNames = new HashSet<>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		if (url != null) {
			String type = url.getProtocol();
			if (FILE_STR.equals(type)) {
				getPackageByFile(url.getPath(), fileNames);
			}
		}
		String path = url.getPath().replaceFirst("/", "").replace("/", ".");
		Set<String> filePathSet = new HashSet<>();
		for (String str : fileNames) {
			str = str.replace("\\", ".");
			String packageEnd = str.replace(path, "");
			filePathSet.add(packageName + packageEnd);
		}
		return filePathSet;
	}

	private static Set<String> getPackageByFile(String packagePath, Set<String> packageNameList) {
		File file = new File(packagePath);
		File[] childFiles = file.listFiles();
		for (
				File childFile : childFiles) {
			if (childFile.isDirectory()) {
				packageNameList.add(childFile.getAbsolutePath());
				getPackageByFile(childFile.getAbsolutePath(), packageNameList);
			}
		}
		return packageNameList;
	}

	public static String[] getClassRequestMapping(Class classes) {
		RequestMapping requestMapping = (RequestMapping) classes.getAnnotation(RequestMapping.class);
		if (requestMapping != null) {
			return requestMapping.value();
		} else {
			return new String[0];
		}
	}

	public static void getRequestMappingMethod(Class classes){
		Method[] methods = classes.getDeclaredMethods();
		for (Method method : methods) {
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
		}
	}
}
