package com.polluxframework.test.utils;

import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhumin0508
 * created in  2018/5/10 8:39
 * modified By:
 */
public class WebTestSingleUtilsTest {

	@Test
	public void getRequest() {
		HttpServletRequest one = WebTestSingleUtils.getSingleRequest();
		HttpServletRequest two = WebTestSingleUtils.getSingleRequest();
		Assert.assertSame(one,two);
	}

	@Test
	public void setAttributeForSession() {
		String name ="caocao";
		String attribute = "曹操";
		WebTestSingleUtils.setAttributeForSession(name,attribute);
		HttpSession httpSession = WebTestSingleUtils.getSession();
		String result = (String) httpSession.getAttribute(name);
		Assert.assertEquals("单例模式下，获取的session是同一个",attribute,result);
	}
}