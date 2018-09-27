package com.polluxframework.version.utils;

import com.polluxframework.version.constant.DataBaseEnum;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


/**
 * @author zhumin0508
 * created in  2018/9/27 10:35
 * modified By:
 */
public class DataBaseUtils {

	public static DataBaseEnum getDbType(Connection connection) throws SQLException {
		DatabaseMetaData meta = connection.getMetaData();
		String dbType = meta.getDatabaseProductName();
		if (DataBaseEnum.MYSQL.match(dbType)) {
			return DataBaseEnum.MYSQL;
		}
		if (DataBaseEnum.ORACLE.match(dbType)) {
			return DataBaseEnum.ORACLE;
		}

		if (DataBaseEnum.DB2.match(dbType)) {
			return DataBaseEnum.DB2;
		}

		return DataBaseEnum.MYSQL;
	}
}
