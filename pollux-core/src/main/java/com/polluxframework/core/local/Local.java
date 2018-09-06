package com.polluxframework.core.local;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * 设置运行时环境，用于在报错等操作时提供多语种提示 默认中文
 *
 * @author zhumin0508
 * created in  2018/5/10 10:10
 * modified By:
 */
public class Local {
	/**
	 * 服务器本地方言
	 */
	private static I18NEnum DEFAULT_LOCALE;

	static {
		Locale locale = Locale.getDefault();
		boolean flag = false;
		for (I18NEnum bean : I18NEnum.values()) {
			if (locale.getLanguage().equals(bean.getLanguage()) && locale.getCountry().equals(bean.getCountry())) {
				DEFAULT_LOCALE = bean;
				flag = true;
				break;
			}
		}
		if (!flag) {
			DEFAULT_LOCALE = I18NEnum.ENGLISH;
		}
	}

	public static I18NEnum getDefaultLocale() {
		return DEFAULT_LOCALE;
	}

	public static I18NEnum getLocale(String language, String country) {
		if (StringUtils.isBlank(language) || StringUtils.isBlank(country)) {
			return DEFAULT_LOCALE;
		}
		for (I18NEnum bean : I18NEnum.values()) {
			if (language.equals(bean.getLanguage()) && country.equals(bean.getCountry())) {
				return bean;
			}
		}
		return DEFAULT_LOCALE;
	}
}
