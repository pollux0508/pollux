package com.polluxframework.test.util;

import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhumin0508
 * created in  2018/5/10 8:39
 * modified By:
 */
public class UnitTestSingleUtilsTest {

	@Test
	public void getRequest() {
		HttpServletRequest one = UnitTestUtils.getSingleRequest();
		HttpServletRequest two = UnitTestUtils.getSingleRequest();
		Assert.assertSame(one,two);
	}

	@Test
	public void setAttributeForSession() {
		String name ="caocao";
		String attribute = "曹操";
		UnitTestUtils.setAttributeForSession(name,attribute);
		HttpSession httpSession = UnitTestUtils.getSession();
		String result = (String) httpSession.getAttribute(name);
		Assert.assertEquals("单例模式下，获取的session是同一个",attribute,result);
	}
}