package com.polluxframework.service;

import com.polluxframework.datasource.DataSource;
import com.polluxframework.entity.Table;
import com.polluxframework.util.JdbcUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * 数据库初始化服务
 *
 * @author zhumin0508
 * created in  2018/8/17 8:06
 * modified By:
 */
public class DataBaseInitJava {
	private javax.sql.DataSource dataSource;

	private static final String PX_CM_TABLE_CREATE = "CREATE TABLE PX_CM_TABLE ( TABLE_NAME VARCHAR(255) NOT NULL, VERSION VARCHAR(8) NOT NULL, FORCE_CREATE CHAR(1) DEFAULT '0' NOT NULL )";

	public DataBaseInitJava(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void init() throws SQLException {
		if (dataSource instanceof DataSource) {
			DataSource hzDataSource = (DataSource) dataSource;
			Map<Object, Object> dataSourceTargetCache = hzDataSource.getTargetCache();
			for (Map.Entry<Object, Object> entry : dataSourceTargetCache.entrySet()) {
				javax.sql.DataSource bean = (javax.sql.DataSource) entry.getValue();
				createCmTable(bean.getConnection());
			}
		} else {
			createCmTable(dataSource.getConnection());
		}
	}

	public void createCmTable(Connection connection) throws SQLException {
		try {
			Table table = JdbcUtils.getTableByName(connection, null, "PX_CM_TABLE");
			if (table == null || StringUtils.isEmpty(table.getTable())) {
				JdbcUtils.createTable(connection, PX_CM_TABLE_CREATE);
			}
		} finally {
			connection.close();
		}
	}
}
