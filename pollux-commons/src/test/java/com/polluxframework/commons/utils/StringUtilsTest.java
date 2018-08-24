package com.polluxframework.commons.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhumin0508
 * created in  2018/8/23 9:02
 * modified By:
 */
public class StringUtilsTest {

	@Test
	public void isContains() {
		String container = "abc";
		String[] ary = {null, "", "a", "ab", "abc"};
		Set<String> set = new HashSet<>();
		set.add(null);
		set.add("");
		set.add("a");
		set.add("ab");
		set.add("abc");
		Assert.assertTrue(StringUtils.isContains(container, ary));
		Assert.assertTrue(StringUtils.isContains(container, set));
		container = "abcd";
		Assert.assertTrue(StringUtils.isContains(container, ary));
		Assert.assertTrue(StringUtils.isContains(container, set));
		container = null;
		Assert.assertFalse(StringUtils.isContains(container, ary));
		Assert.assertFalse(StringUtils.isContains(container, set));
		container = "";
		Assert.assertTrue(StringUtils.isContains(container, ary));
		Assert.assertTrue(StringUtils.isContains(container, set));
		container = "bcefg";
		Assert.assertFalse(StringUtils.isContains(container, ary));
		Assert.assertFalse(StringUtils.isContains(container, set));
	}


	@Test
	public void replaceBlank() {
		String str=null;
		Assert.assertNull(StringUtils.replaceBlank(str));
		str="";
		Assert.assertEquals(str,StringUtils.replaceBlank(str));
		str="abc";
		Assert.assertEquals(str,StringUtils.replaceBlank(str));
		str="a\r\nb\r\tc \n";
		String result= StringUtils.replaceBlank(str);
		Assert.assertNotEquals(str,result);
		Assert.assertEquals("abc",result);
	}
}