package com.polluxframework.core.local;

import org.junit.Test;

import java.util.Locale;

/**
 * @author zhumin0508
 * created in  2018/9/5 14:28
 * modified By:
 */
public class LocalTest {

	@Test
	public void getDefaultLanguage() {
		System.out.println(Local.getLocale("en","US"));

		Locale defaultLocale = new Locale("en", "US");
		String country = defaultLocale.getCountry();//返回国家地区代码
		System.out.println(country);
		String language = defaultLocale.getLanguage();//返回国家的语言
		System.out.println(language);
		String displayCountry = defaultLocale.getDisplayCountry();//返回适合向用户显示的国家信息
		System.out.println(displayCountry);
		String displayLanguage = defaultLocale.getDisplayLanguage();//返回适合向用户展示的语言信息
		System.out.println(displayLanguage);
		String displayName = defaultLocale.getDisplayName();//返回适合向用户展示的语言环境名
		System.out.println(displayName);
	}
}