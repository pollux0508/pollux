package com.polluxframework.commons.entity;

/**
 * @author zhumin0508
 * created in  2018/5/11 11:19
 * modified By:
 */
public interface IPagination {
	/**
	 * 获取页码Key
	 * @return key
	 */
	String getPageKey();

	/**
	 * 设置当前页码key
	 * @param pageKey key
	 */
	void setPageKey(String pageKey);

	/**
	 * 获取当前页码
	 * @return 页码
	 */
	Integer getPageNo();

	/**
	 * 设置当前页码
	 * @param pageNo 页码
	 */
	void setPageNo(Integer pageNo);

	/**
	 * 获取当前页码Size
	 * @return 页码Size
	 */
	Integer getPageSize();

	/**
	 * 获取当前页码Size
	 * @param pageSize 页码Size
	 */
	void setPageSize(Integer pageSize);
}
