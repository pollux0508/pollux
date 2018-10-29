package com.polluxframework.commons.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/5/8 14:03
 * modified By:
 */
public class Bean2MapUtils {
	private Bean2MapUtils() {

	}

	/**
	 * 将一个对象转换成一个Map对象
	 *
	 * @param bean 实体对象
	 * @return 返回一个Map
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = new HashMap<>(8);
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			Iterator<Map.Entry<String, Object>> it = beanMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}


	/**
	 * 将Map 转换成Bean对象
	 *
	 * @param map  存放数据的Map
	 * @param bean 返回类型的Bean
	 * @return 返回一个bean对象
	 */
	public static <T> T mapToBean(Map<String, Object> map, T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}
}