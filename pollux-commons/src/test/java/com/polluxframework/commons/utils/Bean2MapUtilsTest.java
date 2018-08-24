package com.polluxframework.commons.utils;

import com.polluxframework.commons.entity.Pagination;
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
		Pagination pagination = new Pagination();
		Map<String, Object> map = Bean2MapUtils.beanToMap(pagination);
		Assert.assertEquals(1,map.get("pageNo"));

		pagination = null;
		map = Bean2MapUtils.beanToMap(pagination);
		Assert.assertNull(map.get("pageNo"));

	}

	@Test
	public void mapToBean() {
		Map<String, Object> map= new HashMap<>();
		map.put("pageKey","zhumin");
		map.put("pageNo",20);
		Pagination pagination = new Pagination();
		pagination=Bean2MapUtils.mapToBean(map,pagination);
		Assert.assertEquals("zhumin",pagination.getPageKey());
		Assert.assertEquals(Integer.valueOf(20),pagination.getPageNo());
	}
}