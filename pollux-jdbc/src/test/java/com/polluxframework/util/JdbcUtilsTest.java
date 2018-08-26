package com.polluxframework.util;

import com.polluxframework.entity.Table;
import com.polluxframework.exception.BaseException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * @author zhumin0508
 * created in  2018/8/8 8:53
 * modified By:
 */
public class JdbcUtilsTest {

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.65.152:3306/station_0?useUnicode=true&characterEncoding=utf8";
	private String url2 = "jdbc:mysql://192.168.65.152:3306/hzinfo?useUnicode=true&characterEncoding=utf8";
	private String username = "root";
	private String password = "123";

	@Test
	public void getConnection() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection(driver, url, username, password);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
	}

	@Test
	public void getAllTableNames() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection(driver, url, username, password);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<String> result = null;
		try {
			result = JdbcUtils.getAllTableNamesBySchema(connection,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表名失败", result);
	}

	@Test
	public void getAllTableNamesBySchema() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection(driver, url, username, password);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<String> result = null;
		try {
			result = JdbcUtils.getAllTableNamesBySchema(connection, "hzinfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表名失败", result);
	}

	@Test
	public void getAllTable() {
	}

	@Test
	public void getAllSchemas() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection(driver, url, username, password);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<String> result = null;
		try {
			result = JdbcUtils.getAllSchemas(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表名失败", result);
	}

	@Test
	public void getAllTable1() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection(driver, url, username, password);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<Table> tables = null;
		try {
			tables = JdbcUtils.getAllTable(connection, "station_0");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表失败", tables);
	}

	@Test
	public void getTableByName() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection(driver, url, username, password);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		Table table = null;
		try {
			table = JdbcUtils.getTableByName(connection, "station_0", "assess_items");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表失败", table);

	}

	@Test
	public void createTable() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection(driver, url, username, password);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		Table table = null;
		try {
			table = JdbcUtils.getTableByName(connection, "station_0", "assess_item_score");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表失败", table);
		try {
			JdbcUtils.closeConnection(connection);
			connection = JdbcUtils.getConnection(driver, url2, username, password);
			JdbcUtils.forceCreateTable(connection,table,0);
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}