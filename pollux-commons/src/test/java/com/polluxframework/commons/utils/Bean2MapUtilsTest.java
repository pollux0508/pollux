package com.polluxframework.commons.utils;

import com.polluxframework.commons.entity.TextLabel;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/5/10 15:38
 * modified By:
 */
public class Bean2MapUtilsTest {

	@Test
	public void beanToMap() {
		TextLabel pagination = new TextLabel();
		pagination.setIndexPos(1);
		Map<String, Object> map = Bean2MapUtils.beanToMap(pagination);
		Assert.assertEquals(1,map.get("indexPos"));

		pagination = null;
		map = Bean2MapUtils.beanToMap(pagination);
		Assert.assertNull(map.get("pageNo"));

	}

	@Test
	public void mapToBean() {
		Map<String, Object> map= new HashMap<>();
		map.put("pageKey","zhumin");
		map.put("pageNo",20);
		TextLabel pagination = new TextLabel();
		pagination=Bean2MapUtils.mapToBean(map,pagination);
		Assert.assertEquals(0,pagination.getIndexPos());
	}
}