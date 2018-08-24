package com.polluxframework.service;

import com.polluxframework.datasource.PxDataSource;
import com.polluxframework.entity.PxTable;
import com.polluxframework.util.PxJdbcUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
	private DataSource dataSource;

	private static final String PX_CM_TABLE_CREATE = "CREATE TABLE PX_CM_TABLE ( TABLE_NAME VARCHAR(255) NOT NULL, VERSION VARCHAR(8) NOT NULL, FORCE_CREATE CHAR(1) DEFAULT '0' NOT NULL )";

	public DataBaseInitJava(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void init() throws SQLException {
		if (dataSource instanceof PxDataSource) {
			PxDataSource hzDataSource = (PxDataSource) dataSource;
			Map<Object, Object> dataSourceTargetCache = hzDataSource.getTargetCache();
			for (Map.Entry<Object, Object> entry : dataSourceTargetCache.entrySet()) {
				DataSource bean = (DataSource) entry.getValue();
				createCmTable(bean.getConnection());
			}
		} else {
			createCmTable(dataSource.getConnection());
		}
	}

	public void createCmTable(Connection connection) throws SQLException {
		try {
			PxTable table = PxJdbcUtils.getTableByName(connection, null, "PX_CM_TABLE");
			if (table == null || StringUtils.isEmpty(table.getTable())) {
				PxJdbcUtils.createTable(connection, PX_CM_TABLE_CREATE);
			}
		} finally {
			connection.close();
		}
	}
}
