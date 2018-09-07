package com.polluxframework.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

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
		String methodName = "get" + toUpperCaseFirstLetter(field);
		try {
			Method method = target.getClass().getMethod(methodName);
			return method.invoke(target);
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
}
