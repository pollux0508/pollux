package com.polluxframework.database.utils;

import com.polluxframework.database.constant.DataBaseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


/**
 * @author zhumin0508
 * created in  2018/9/27 10:35
 * modified By:
 */
public class DataBaseUtils {
	private static final Logger logger = LoggerFactory.getLogger(DataBaseUtils.class);

	private DataBaseUtils() {
	}

	public static DataBaseEnum getDbType(Connection connection) throws SQLException {
		DatabaseMetaData meta = connection.getMetaData();
		String dbType = meta.getDatabaseProductName();
		dbType = dbType.toUpperCase();
		try {
			return DataBaseEnum.valueOf(dbType);
		} catch (Exception e) {
			logger.debug("适配类型错误，将采用默认类型");
		}
		return DataBaseEnum.MYSQL;
	}
}
