package com.polluxframework.database.entity;

import java.util.List;

/**
 * 模块版本统一接口
 *
 * @author zhumin0508
 * created in  2018/9/26 14:57
 * modified By:
 */
public interface IModuleVersion {
	/**
	 * 变更SQL存放根目录
	 *
	 * @return SQL存放根目录
	 */
	String getSQLDirectory();

	/**
	 * 获取模块名
	 *
	 * @return 返回模块名
	 */
	String getModule();

	/**
	 * 获取当前版本号
	 *
	 * @return 返回当前版本号
	 */
	String curVersion();

	/**
	 * 获取历史版本
	 *
	 * @return 返回历史版本号
	 */
	List<String> getHistoryVersion();
}
