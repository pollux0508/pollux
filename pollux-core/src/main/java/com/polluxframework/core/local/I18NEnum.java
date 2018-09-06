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
	CHINESE("zh","CN","中国","中文"),
	/**
	 * 英文
	 */
	ENGLISH("en","US","美国","英文");

	private String language;

	private String country;

	private String displayLanguage;

	private String displayCountry;

	I18NEnum(String language, String country, String displayCountry, String displayLanguage) {
		this.language = language;
		this.country = country;
		this.displayLanguage = displayLanguage;
		this.displayCountry = displayCountry;
	}

	public String getLanguage() {
		return language;
	}

	public String getCountry() {
		return country;
	}

	public String getDisplayLanguage() {
		return displayLanguage;
	}

	public String getDisplayCountry() {
		return displayCountry;
	}
}
