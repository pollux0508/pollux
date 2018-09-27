package com.polluxframework.version.service.impl;

import com.polluxframework.version.constant.DataBaseEnum;
import com.polluxframework.version.constant.SchemaEnum;
import com.polluxframework.version.entity.IModuleVersion;
import com.polluxframework.version.scanner.DataBaseScanner;
import com.polluxframework.version.service.DataBaseService;
import com.polluxframework.version.utils.DataBaseUtils;
import com.polluxframework.version.utils.ScriptScannerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhumin0508
 * created in  2018/9/27 8:34
 * modified By:
 */
public class DataBaseServiceImpl implements DataBaseService {
	private static final Logger logger = LoggerFactory.getLogger(DataBaseServiceImpl.class);

	private static final String IS_EXIST_SQL = "SELECT COUNT(1) FROM PX_MODULE_VERSION";

	private static final String CREATE_SQL = "CREATE TABLE PX_MODULE_VERSION( MODULE_NAME VARCHAR(64) NOT NULL, VERSION VARCHAR(8) NOT NULL, HISTORY VARCHAR(300), PRIMARY KEY (MODULE_NAME) ) COMMENT='公共版本表'";

	private static final String MODULE_VERSION_SQL = "SELECT VERSION,HISTORY FROM PX_MODULE_VERSION WHERE MODULE_NAME=?";

	private static final String MODULE_INSERT_SQL = "INSERT INTO PX_MODULE_VERSION(MODULE_NAME, VERSION, HISTORY) VALUES (?,?,?)";

	private static final String MODULE_UPDATE_SQL = "UPDATE PX_MODULE_VERSION SET VERSION=?,HISTORY=? WHERE MODULE_NAME=?";


	private DataSource dataSource;

	private void checkModuleTable(Connection connection) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST_SQL)) {
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.info("公共版本表不存在");
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL);
			preparedStatement.execute();
			logger.info("创建公共版本表成功!");
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void initExistStation() throws SQLException, IOException {
		createSingleStation(dataSource);
	}

	@Override
	public void createSingleStation(DataSource dataSource) throws SQLException, IOException {
		Connection connection = dataSource.getConnection();
		checkModuleTable(connection);
		DataBaseEnum dbType = DataBaseUtils.getDbType(connection);

		Set<IModuleVersion> versions = DataBaseScanner.getDataBaseScannerTables();
		connection.setAutoCommit(false);

		for (IModuleVersion version : versions) {
			PreparedStatement preparedStatement = connection.prepareStatement(MODULE_VERSION_SQL);
			List<String> sqlList;
			preparedStatement.setString(1, version.getModule());
			ResultSet resultSet = preparedStatement.executeQuery();
			String history = "";
			boolean update = false;
			if (resultSet.next()) {
				String curVersion = resultSet.getString(1);
				history = resultSet.getString(2);
				sqlList = getUpdateModuleSql(version, curVersion, dbType);
				update = true;
			} else {
				sqlList = ScriptScannerUtils.readScript(version, dbType, SchemaEnum.CREATE, null);
			}

			if (sqlList == null || sqlList.isEmpty()) {
				continue;
			}
			for (String sql : sqlList) {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.execute();
			}
			if (update) {
				preparedStatement = connection.prepareStatement(MODULE_UPDATE_SQL);
				preparedStatement.setString(1, version.curVersion());
				preparedStatement.setString(2, history + " update to (" + version.curVersion() + ")");
				preparedStatement.setString(3, version.getModule());
			} else {
				preparedStatement = connection.prepareStatement(MODULE_INSERT_SQL);
				preparedStatement.setString(1, version.getModule());
				preparedStatement.setString(2, version.curVersion());
				preparedStatement.setString(3, "create(" + version.curVersion() + ")");
			}
			preparedStatement.execute();
			connection.commit();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.info("关闭链接失败!");
		}
	}

	private List<String> getUpdateModuleSql(IModuleVersion version, String curVersion, DataBaseEnum dbType) throws IOException {
		List<String> sqlList = new ArrayList<>(16);
		boolean flag = false;
		if (!curVersion.equals(version.curVersion())) {
			for (String bean : version.getHistoryVersion()) {
				if (curVersion.equals(bean)) {
					flag = true;
					continue;
				}
				if (flag) {
					sqlList.addAll(ScriptScannerUtils.readScript(version, dbType, SchemaEnum.UPDATE, bean));
				}
			}
		}
		return sqlList;
	}
}
