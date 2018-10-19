package com.polluxframework.database.service.impl;

import com.polluxframework.database.constant.DataBaseEnum;
import com.polluxframework.database.constant.SchemaEnum;
import com.polluxframework.database.datasource.AbstractRoutingDataSource;
import com.polluxframework.database.entity.IModuleVersion;
import com.polluxframework.database.scanner.DataBaseScanner;
import com.polluxframework.database.service.DataBaseService;
import com.polluxframework.database.utils.DataBaseUtils;
import com.polluxframework.database.utils.ScriptScannerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.*;

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

	private static final String MODULE_INSERT_SQL = "INSERT INTO PX_MODULE_VERSION(VERSION,HISTORY,MODULE_NAME) VALUES (?,?,?)";

	private static final String MODULE_UPDATE_SQL = "UPDATE PX_MODULE_VERSION SET VERSION=?,HISTORY=? WHERE MODULE_NAME=?";

	private static final int HISTORY_SIZE = 300;

	private DataSource dataSource;

	private void checkModuleTable(Connection connection) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST_SQL)) {
			preparedStatement.execute();
		} catch (SQLException e) {
			logger.info("公共版本表不存在");
			try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL)) {
				preparedStatement.execute();
				logger.info("创建公共版本表成功!");
			}
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void init() throws IOException, SQLException {
		initExistStation();
	}

	@Override
	public void initExistStation() throws SQLException, IOException {
		if (dataSource instanceof AbstractRoutingDataSource) {
			AbstractRoutingDataSource routingDataSource = (AbstractRoutingDataSource) dataSource;
			Map<Object, DataSource> dataSourceMap = routingDataSource.getResolvedDataSources();
			if (dataSourceMap != null) {
				for (DataSource value : dataSourceMap.values()) {
					createSingleStation(value);
				}
			}
		} else {
			createSingleStation(dataSource);
		}

	}

	@Override
	public void createSingleStation(DataSource dataSource) throws SQLException, IOException {
		Connection connection = dataSource.getConnection();
		checkModuleTable(connection);
		DataBaseEnum dbType = DataBaseUtils.getDbType(connection);

		Map<String, IModuleVersion> versions = DataBaseScanner.getDataBaseScannerTables();

		Set<String> hasInitModules = new LinkedHashSet<>(versions.size());

		for (IModuleVersion version : versions.values()) {
			if (hasInitModules.contains(version.getModule())) {
				continue;
			}
			initModule(connection, version, dbType, hasInitModules, versions);
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.info("关闭链接失败!");
		}
	}

	private void initModule(Connection connection, IModuleVersion version, DataBaseEnum dbType, Set<String> hasInitModules, Map<String, IModuleVersion> versions) throws SQLException, IOException {
		if (version != null) {
			return;
		}
		VersionInfo versionInfo = getCurInfo(connection, version);
		List<String> sqlList;
		boolean update = false;
		if (versionInfo == null) {
			sqlList = ScriptScannerUtils.readScript(version, dbType, SchemaEnum.CREATE, null);
		} else {
			update = true;
			sqlList = getUpdateModuleSql(version, dbType, versionInfo.getVersion());
		}

		if (!sqlList.isEmpty()) {
			executeBatch(connection, sqlList);
			executeModify(connection, version.curVersion(), version.getModule(), versionInfo == null ? null : versionInfo.getHistory(), update);
		}
		hasInitModules.add(version.getModule());
		List<String> prevModules = version.prevModules();
		if (prevModules != null && (!prevModules.isEmpty())) {
			for (String module : prevModules) {
				IModuleVersion bean = versions.get(module);
				initModule(connection, bean, dbType, hasInitModules, versions);
			}
		}
	}

	private VersionInfo getCurInfo(Connection connection, IModuleVersion version) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MODULE_VERSION_SQL)) {
			preparedStatement.setString(1, version.getModule());
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					VersionInfo versionInfo = new VersionInfo();
					versionInfo.setVersion(resultSet.getString(1));
					versionInfo.setHistory(resultSet.getString(2));
					return versionInfo;
				}
			}
		}
		return null;
	}

	private void executeBatch(Connection connection, List<String> sqlList) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			int count = 0;
			for (String sql : sqlList) {
				statement.addBatch(sql);
				if (++count % 1000 == 0) {
					statement.executeBatch();
				}
			}
			statement.executeBatch();
		}
	}

	private void executeModify(Connection connection, String version, String module, String history, boolean update) throws SQLException {
		String sql = MODULE_INSERT_SQL;
		String historyStr = "create(" + version + ")";
		if (update) {
			sql = MODULE_UPDATE_SQL;
			historyStr = history + " update to (" + version + ")";
		}
		int size = historyStr.length();
		if (size > HISTORY_SIZE) {
			historyStr = historyStr.substring(size - 301);
		}
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, version);
			preparedStatement.setString(2, historyStr);
			preparedStatement.setString(3, module);
			preparedStatement.execute();
		}
	}


	private List<String> getUpdateModuleSql(IModuleVersion version, DataBaseEnum dbType, String curVersion) throws IOException {
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

class VersionInfo {
	private String version;
	private String history;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
}