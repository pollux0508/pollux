package com.polluxframework.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author zhumin0508
 * created in  2018/9/7 9:34
 * modified By:
 */
public class ReflectionUtilsTest {

	@Test
	public void getFieldValueByName() throws ReflectiveOperationException {
		Column bean = new Column();
		bean.setName("中文");
		Object object = ReflectionUtils.getFieldValueByName(bean, "name", null);
		Assert.assertEquals("正确获取到字段名", bean.getName(), object);

		bean.setRowspan(1);
		object = ReflectionUtils.getFieldValueByName(bean, "rowspan", null);
		Assert.assertEquals("单元格应占用的行数", bean.getRowspan(), object);

		object = ReflectionUtils.getFieldValueByName(bean, "x", null);
		Assert.assertNull("没有‘x’属性，应该返回默认值 null ", object);

		object = ReflectionUtils.getFieldValueByName(bean, "children", null);
		Assert.assertNull("children未初始化，应该返回 null", object);

		bean.setChildren(new ArrayList<>());
		object = ReflectionUtils.getFieldValueByName(bean, "children", null);
		Assert.assertNotNull("children已初始化，不应该返回 null", object);

		bean.setHalign("right");
		object = ReflectionUtils.getFieldValueByName(bean, "halign", null);
		Assert.assertNull("halign是一个被保护的对象，不能被被访问，应该返回 null", object);

		Column temp = new Column();
		temp.setName("中文");
		object = ReflectionUtils.getFieldValueByName(temp, "name");
		Assert.assertEquals("正确获取到字段名", temp.getName(), object);

		temp.setRowspan(1);
		object = ReflectionUtils.getFieldValueByName(temp, "rowspan");
		Assert.assertEquals("单元格应占用的行数", temp.getRowspan(), object);
		boolean flag;
		try {
			ReflectionUtils.getFieldValueByName(temp, "x");
			flag = false;
		} catch (ReflectiveOperationException e) {
			flag = true;
		}
		Assert.assertTrue("没有‘x’属性，此处应该抛出异常", flag);

		object = ReflectionUtils.getFieldValueByName(temp, "children");
		Assert.assertNull("children未初始化，应该返回 null", object);

		temp.setChildren(new ArrayList<>());
		object = ReflectionUtils.getFieldValueByName(temp, "children");
		Assert.assertNotNull("children已初始化，不应该返回 null", object);

		temp.setHalign("right");
		try {
			ReflectionUtils.getFieldValueByName(temp, "halign");
			flag = false;
		} catch (ReflectiveOperationException e) {
			flag = true;

		}
		Assert.assertTrue("halign是一个被保护的对象，不能被被访问，此处应该抛出异常", flag);
	}
}