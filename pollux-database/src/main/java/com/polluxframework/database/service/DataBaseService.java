package com.polluxframework.database.service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author zhumin0508
 * created in  2018/9/27 8:34
 * modified By:
 */
public interface DataBaseService {


	/**
	 * 初始化所有站点的业务表
	 *
	 * @throws SQLException SQL执行异常
	 * @throws IOException  获取站点业务表异常
	 */
	void initExistStation() throws SQLException, IOException;

	/**
	 * 根据站点信息初始化站点业务表
	 *
	 * @param dataSource 站点数据源
	 * @throws SQLException SQL执行异常
	 * @throws IOException  获取站点业务表异常
	 */
	void createSingleStation(DataSource dataSource) throws SQLException, IOException;
}
