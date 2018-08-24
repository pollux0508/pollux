package com.polluxframework.util;

import com.polluxframework.entity.PxTable;
import com.polluxframework.exception.PxBaseException;
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
public class PxJdbcUtilsTest {

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.65.152:3306/station_0?useUnicode=true&characterEncoding=utf8";
	private String url2 = "jdbc:mysql://192.168.65.152:3306/hzinfo?useUnicode=true&characterEncoding=utf8";
	private String username = "root";
	private String password = "123";

	@Test
	public void getConnection() {
		Connection connection = null;
		try {
			connection = PxJdbcUtils.getConnection(driver, url, username, password);
		} catch (PxBaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
	}

	@Test
	public void getAllTableNames() {
		Connection connection = null;
		try {
			connection = PxJdbcUtils.getConnection(driver, url, username, password);
		} catch (PxBaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<String> result = null;
		try {
			result = PxJdbcUtils.getAllTableNamesBySchema(connection,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表名失败", result);
	}

	@Test
	public void getAllTableNamesBySchema() {
		Connection connection = null;
		try {
			connection = PxJdbcUtils.getConnection(driver, url, username, password);
		} catch (PxBaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<String> result = null;
		try {
			result = PxJdbcUtils.getAllTableNamesBySchema(connection, "hzinfo");
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
			connection = PxJdbcUtils.getConnection(driver, url, username, password);
		} catch (PxBaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<String> result = null;
		try {
			result = PxJdbcUtils.getAllSchemas(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表名失败", result);
	}

	@Test
	public void getAllTable1() {
		Connection connection = null;
		try {
			connection = PxJdbcUtils.getConnection(driver, url, username, password);
		} catch (PxBaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		List<PxTable> tables = null;
		try {
			tables = PxJdbcUtils.getAllTable(connection, "station_0");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表失败", tables);
	}

	@Test
	public void getTableByName() {
		Connection connection = null;
		try {
			connection = PxJdbcUtils.getConnection(driver, url, username, password);
		} catch (PxBaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		PxTable table = null;
		try {
			table = PxJdbcUtils.getTableByName(connection, "station_0", "assess_items");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表失败", table);

	}

	@Test
	public void createTable() {
		Connection connection = null;
		try {
			connection = PxJdbcUtils.getConnection(driver, url, username, password);
		} catch (PxBaseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取链接失败", connection);
		PxTable table = null;
		try {
			table = PxJdbcUtils.getTableByName(connection, "station_0", "assess_item_score");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull("获取表失败", table);
		try {
			PxJdbcUtils.closeConnection(connection);
			connection = PxJdbcUtils.getConnection(driver, url2, username, password);
			PxJdbcUtils.forceCreateTable(connection,table,0);
		} catch (PxBaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}