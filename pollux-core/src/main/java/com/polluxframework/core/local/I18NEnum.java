package com.polluxframework.core.local;

/**
 * 提供方言常量
 *
 * @author zhumin0508
 * created in  2018/5/10 10:14
 * modified By:
 */
public enum I18NEnum {
	/**
	 * 中文
	 */
	CHINESE("zh_CH"),
	/**
	 * 英文
	 */
	ENGLISH("en_US");

	private String language;

	I18NEnum(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
}
