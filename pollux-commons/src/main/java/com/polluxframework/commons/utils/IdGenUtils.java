package com.polluxframework.commons.utils;

import java.util.UUID;

/**
 * 统一ID生成
 * @author zhumin0508
 * created in  2018/5/8 13:44
 * modified By:
 */
public class IdGenUtils {

	private IdGenUtils(){

	}

	/**
	 * 获取下一个ID
	 * @return 下一个ID
	 */
	public static String getNextId() {
		return uuid();
	}

	/**
	 * 获取指定格式的UUID字符串
	 *
	 * @return 获取指定格式的UUID字符串
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
}
