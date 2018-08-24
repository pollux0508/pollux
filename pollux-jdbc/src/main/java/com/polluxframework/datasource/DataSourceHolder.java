package com.polluxframework.datasource;

/**
 * 使用线程安全的对象保存数据源标识
 *
 * @author zhumin0508
 * created in  2018/8/17 9:56
 * modified By:
 */
public class DataSourceHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();


	private DataSourceHolder() {

	}

	/**
	 * 设置数据源标识
	 *
	 * @param dbType 数据源标识
	 */
	public static synchronized void setDBType(String dbType) {
		contextHolder.set(dbType);
	}

	/**
	 * 获取数据源标识
	 *
	 * @return 返回数据源标识
	 */
	public static synchronized String getDBType() {
		return contextHolder.get();
	}

	/**
	 * 清楚数据源标识，将其还原到默认情况
	 */
	public static void clearDBType() {
		contextHolder.remove();
	}
}
