package com.polluxframework.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

import static com.polluxframework.commons.utils.StringUtils.toUpperCaseFirstLetter;

/**
 * @author zhumin0508
 * created in  2018/9/7 9:04
 * modified By:
 */
public class ReflectionUtils {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
	}

	/**
	 * 根据字段名获取指定对象的的field字段对应的get方法值，如果获取不成功则返回默认值
	 *
	 * @param target       目标对象
	 * @param field        字段名称
	 * @param defaultValue 默认值
	 * @return 返回目标对象的值
	 */
	public static Object getFieldValueByName(Object target, String field, Object defaultValue) {
		try {
			return getFieldValueByName(target, field);
		} catch (ReflectiveOperationException e) {
			logger.warn("找不到匹配的方法或调用目标异常", e);
		}
		return defaultValue;
	}

	/**
	 * 根据字段名获取指定对象的的field字段对应的get方法值
	 *
	 * @param target 目标对象
	 * @param field  字段名称
	 * @return 返回目标对象的值
	 * @throws ReflectiveOperationException 获取值时可能由于某些原型会抛出无法找到匹配的方法等异常
	 */
	public static Object getFieldValueByName(Object target, String field) throws ReflectiveOperationException {
		String methodName = "get" + toUpperCaseFirstLetter(field);
		Method method = target.getClass().getMethod(methodName);
		return method.invoke(target);
	}

	/**
	 * 根据字段名获取指定Map的的field字段对应的值
	 *
	 * @param target       目标Map对象
	 * @param field        字段名称
	 * @param defaultValue 默认值
	 * @return 返回目标对象的值
	 */
	public static Object getFieldValueByName(Map<String, Object> target, String field, Object defaultValue) {
		Object value = target.get(field);
		if(value==null){
			logger.warn("Map对象中找不到对应的值");
			return defaultValue;
		}
		return value;
	}
}
