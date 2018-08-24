package com.polluxframework.core.local;

/**
 * 设置运行时环境，用于在报错等操作时提供多语种提示 默认中文
 *
 * @author zhumin0508
 * created in  2018/5/10 10:10
 * modified By:
 */
public class Local {

	private static String defaultLanguage = I18NEnum.CHINESE.getLanguage();

	public void setLocal(String language) {
		Local.setDefaultLanguage(language);
	}

	public static void setDefaultLanguage(String language) {
		defaultLanguage = language;
	}

	public static String getDefaultLanguage() {
		return defaultLanguage;
	}
}
