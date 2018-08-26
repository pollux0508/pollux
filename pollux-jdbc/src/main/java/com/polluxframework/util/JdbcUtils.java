package com.polluxframework.util;

import com.polluxframework.entity.Column;
import com.polluxframework.entity.Index;
import com.polluxframework.entity.Table;
import com.polluxframework.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/8 8:30
 * modified By:
 */
public class JdbcUtils {

	private static final String TABLE = "TABLE";
	private static final String TABLE_NAME = "TABLE_NAME";
	private static final String TABLE_SCHEME = "TABLE_SCHEM";
	private static final String COMMENT = "REMARKS";
	private static final String UNKNOWN_TABLE_CODE = "42S02";


	private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	private JdbcUtils() {
	}

	/**
	 * @param driver   jdbc驱动
	 * @param url      jdbc数据库url
	 * @param username 数据库用户名
	 * @param password 数据库密码
	 * @return 数据库链接
	 * @throws BaseException 配置异常或驱动找不到
	 */
	public static Connection getConnection(String driver, String url, String username, String password) throws BaseException {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			logger.error("JDBC驱动类: {} 无法找到", driver);
			throw new BaseException("PXKFDB000", "JDBC驱动类无法找到", e);
		} catch (SQLException e) {
			logger.error("无法获取Connection，请确认配置的参数是否正确 url: {}, username: {} ,password: {}", url, username, password);
			throw new BaseException("PXKFDB001", "无法获取Connection，请确认配置的参数是否正确", e);
		}
	}

	/**
	 * @param connection 数据库链接
	 * @return 返回所有实例名(仅对Oracle有效)
	 * @throws SQLException Sql查询异常
	 */
	public static List<String> getAllSchemas(Connection connection) throws SQLException {
		List<String> result = new ArrayList<>(32);
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet schemas = meta.getSchemas();
		while (schemas.next()) {
			String schema = schemas.getString(TABLE_SCHEME);
			result.add(schema);
		}
		schemas.close();
		return result;
	}

	/**
	 * @param connection 数据库链接
	 * @param schema     实例名(仅对Oracle有效) null则返回所有
	 * @return 返回该实例下所有表名
	 * @throws SQLException JDBC异常
	 */
	public static List<String> getAllTableNamesBySchema(Connection connection, String schema) throws SQLException {
		List<String> result = new ArrayList<>(32);
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet tables = getTables(meta, schema, null);
		while (tables.next()) {
			String tableName = tables.getString(TABLE_NAME);
			result.add(tableName);
		}
		tables.close();
		return result;
	}

	/**
	 * @param connection 数据库链接
	 * @param schema     实例名(仅对Oracle有效)  null则返回所有
	 * @return 返回该实例名下所有表
	 * @throws SQLException Sql异常
	 */
	public static List<Table> getAllTable(Connection connection, String schema) throws SQLException {
		List<Table> result = new ArrayList<>(32);
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet tables = getTables(meta, schema, null);
		while (tables.next()) {
			Table table = new Table();
			table.setTable(tables.getString(TABLE_NAME));
			table.setSchema(schema == null ? tables.getString(TABLE_SCHEME) : schema);
			table.setComment(tables.getString(COMMENT));
			result.add(table);
		}
		for (Table table : result) {
			table.setColumns(getColumns(connection, schema, table.getTable()));
		}
		tables.close();
		return result;
	}

	/**
	 * @param connection 数据库链接
	 * @param schema     实例名(仅对Oracle有效)  null则返回所有实例名下的相同表的第一个
	 * @param tableName  表名
	 * @return 获取表结构
	 * @throws SQLException Sql异常
	 */
	public static Table getTableByName(Connection connection, String schema, String tableName) throws SQLException {
		Table result = new Table();
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet tables = getTables(meta, schema, tableName);
		if (tables.next()) {
			result.setTable(tables.getString(TABLE_NAME));
			result.setSchema(schema == null ? tables.getString(TABLE_SCHEME) : schema);
			result.setComment(tables.getString(COMMENT));
		}
		result.setColumns(getColumns(connection, schema, result.getTable()));
		result.setPrimaryKeys(getPrimaryKeys(connection, schema, result.getTable()));
		tables.close();
		return result;
	}

	private static ResultSet getTables(DatabaseMetaData meta, String schema, String tableName) throws SQLException {
		return meta.getTables(null, schema, tableName, new String[]{TABLE});
	}

	/**
	 * @param connection 数据库链接
	 * @param schema     实例名(仅对Oracle有效)  null则返回所有实例名下的相同表的第一个
	 * @param tableName  表名
	 * @return 该表对应的所有列
	 * @throws SQLException Sql异常
	 */
	public static List<Column> getColumns(Connection connection, String schema, String tableName) throws SQLException {
		if(StringUtils.isEmpty(tableName)){
			return null;
		}
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet columns = meta.getColumns(null, schema, tableName, null);
		List<Column> result = getColumnsByRs(columns);
		columns.close();
		return result;
	}

	/**
	 * @param connection 数据库链接
	 * @param schema     实例名(仅对Oracle有效)  null则返回所有实例名下的相同表的第一个
	 * @param tableName  表名
	 * @return 该表对应的主键的相关信息
	 * @throws SQLException Sql异常
	 */
	public static List<Index> getPrimaryKeys(Connection connection, String schema, String tableName) throws SQLException {
		if(StringUtils.isEmpty(tableName)){
			return null;
		}
		List<Index> result = new ArrayList<>(8);
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet rs = meta.getPrimaryKeys(null, schema, tableName);
		while (rs.next()) {
			Index index = new Index();
			index.setTable(tableName);
			index.setColumn(rs.getString("COLUMN_NAME"));
			index.setName(rs.getString("PK_NAME"));
			index.setSeq(rs.getInt("KEY_SEQ"));
			result.add(index);
		}
		rs.close();
		return result;
	}

	/**
	 * @param columns ResultSet列集合
	 * @return 返回列信息
	 * @throws SQLException 获取数据异常
	 */
	private static List<Column> getColumnsByRs(ResultSet columns) throws SQLException {
		List<Column> result = new ArrayList<>(32);
		while (columns.next()) {
			Column column = new Column();
			column.setTable(columns.getString(TABLE_NAME));
			column.setColumn(columns.getString("COLUMN_NAME"));
			column.setType(columns.getString("TYPE_NAME"));
			column.setSize(columns.getString("COLUMN_SIZE"));
			column.setComment(columns.getString(COMMENT));
			column.setScale(columns.getString("DECIMAL_DIGITS"));
			column.setDefaultValue(columns.getString("COLUMN_DEF"));
			column.setNullAble(columns.getString("IS_NULLABLE"));
			column.setAutoIncrement(columns.getString("IS_AUTOINCREMENT"));
			result.add(column);
		}
		return result;
	}

	/**
	 * @param connection 数据库链接
	 * @throws SQLException 关闭链接异常
	 */
	public static void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

	/**
	 * 建表
	 *
	 * @param connection 数据库链接
	 * @param pxTable    表信息
	 * @param dbType     数据库类型 0 Mysql  其他为标准sql数据库
	 * @throws SQLException SQL异常
	 */
	public static void createTable(Connection connection, Table pxTable, int dbType) throws SQLException {
		String sql = pxTable.getCreateTable(dbType);
		logger.debug("执行建表SQL：{}", sql);
		execute(connection, sql);

		sql = pxTable.getPrimaryKey(dbType);
		if (StringUtils.isNotEmpty(sql)) {
			logger.debug("执行建主键SQL：{}", sql);
			execute(connection, sql);
		}
	}

	/**
	 * 强制建表，先删除表在建新的表
	 *
	 * @param connection 数据库链接
	 * @param pxTable    表信息
	 * @param dbType     数据库类型 0 Mysql  其他为标准sql数据库
	 * @throws SQLException SQL异常
	 */
	public static void forceCreateTable(Connection connection, Table pxTable, int dbType) throws SQLException {
		String sql = pxTable.getDropTable();
		logger.debug("执行删除表SQL：{}", sql);
		dropTable(connection, sql);

		createTable(connection, pxTable, dbType);
	}

	/**
	 * 建表
	 *
	 * @param connection 数据库链接
	 * @param sql        建表语句
	 * @throws SQLException SQL异常
	 */
	public static void createTable(Connection connection, String sql) throws SQLException {
		execute(connection, sql);
	}

	/**
	 * 修改表信息
	 *
	 * @param connection 数据库链接
	 * @param sql        修改表
	 * @throws SQLException SQL异常
	 */
	public static void alertTable(Connection connection, String sql) throws SQLException {
		execute(connection, sql);
	}

	/**
	 * 删除表
	 *
	 * @param connection 数据库链接
	 * @param sql        删除表语句
	 * @return 是否删除成功
	 */
	public static boolean dropTable(Connection connection, String sql) {
		try {
			execute(connection, sql);
			return true;
		} catch (SQLException e) {
			if (UNKNOWN_TABLE_CODE.equals(e.getSQLState())) {
				logger.error("表不存在或你登录的用户没有删表权限,将进行强制建表", e);
			} else {
				logger.error("删除表失败,将进行强制建表", e);
			}
			return false;
		}
	}

	/**
	 * 执行任意SQL
	 *
	 * @param connection 数据库链接
	 * @param sql        任意SQL
	 * @throws SQLException SQL异常
	 */
	public static void execute(Connection connection, String sql) throws SQLException {
		PreparedStatement statement = null;
		try {
			logger.debug("即将执行的SQL：{}", sql);
			statement = connection.prepareStatement(sql);
			statement.execute(sql);
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public static int executeCountQuery(Connection connection, String sql) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			logger.debug("即将执行的SQL：{}", sql);
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(0);
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return 0;
	}
}
