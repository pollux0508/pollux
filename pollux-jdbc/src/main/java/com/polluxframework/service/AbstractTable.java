package com.polluxframework.service;

import com.polluxframework.util.JdbcUtils;
import com.polluxframework.entity.Table;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author zhumin0508
 * created in  2018/8/9 9:14
 * modified By:
 */
public abstract class AbstractTable {
    private static final Logger logger = LoggerFactory.getLogger(AbstractTable.class);

    private static final String FORCE_CREATE = "1";
    private static final Integer INIT_VERSION = 1;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 实例名
     */
    private String schema;
    /**
     * 是否强制建表
     */
    private String force;

    public boolean getForceTable() {
        return FORCE_CREATE.equals(force);
    }

    public AbstractTable(String schema, String tableName, String force) {
        this.version = INIT_VERSION;
        this.schema = schema;
        this.tableName = tableName;
        this.force = force;
    }

    public AbstractTable(Integer version, String schema, String tableName, String force) {
        this.version = version;
        this.schema = schema;
        this.tableName = tableName;
        this.force = force;
    }


    public void execute(Connection connection) {
        try {
            String dbType = connection.getMetaData().getDatabaseProductName().toUpperCase();
            connection.setAutoCommit(false);
            //是否进行表删除
            if (FORCE_CREATE.equals(force)) {
                forceCreate(connection, dbType);
            } else if (isExistTable(connection)) {
                update(connection, dbType);
            } else {
                create(connection, dbType);
            }
            deleteVersion(connection);
            insertVersion(connection);
            connection.commit();
        } catch (SQLException e) {
            logger.error("执行表结构失败，即将进行回滚", e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error("回滚失败", e1);
            }
        }

    }

    /**
     * 强制建表
     *
     * @param connection 数据库连接
     * @throws SQLException SQL异常
     */
    private void forceCreate(Connection connection, String dbType) throws SQLException {
        String sql = getDropSql(dbType);
        if (StringUtils.isNotEmpty(sql)) {
            JdbcUtils.dropTable(connection, sql);
        }
        //创建表
        sql = getCreateSql(dbType);
        if (StringUtils.isNotEmpty(sql)) {
            JdbcUtils.createTable(connection, sql);
        }
        sql = getPrimaryKeySql(dbType);
        if (StringUtils.isNotEmpty(sql)) {
            JdbcUtils.execute(connection, sql);
        }
    }

    /**
     * 建表
     *
     * @param connection 数据库连接
     * @throws SQLException SQL异常
     */
    private void create(Connection connection, String dbType) throws SQLException {
        //创建表
        String sql = getCreateSql(dbType);
        if (StringUtils.isNotEmpty(sql)) {
            JdbcUtils.createTable(connection, sql);
        }
        sql = getPrimaryKeySql(dbType);
        if (StringUtils.isNotEmpty(sql)) {
            JdbcUtils.execute(connection, sql);
        }
    }

    /**
     * 修改表信息
     *
     * @param connection 数据库连接
     * @throws SQLException SQL异常
     */
    private void update(Connection connection, String dbType) throws SQLException {
        if (!isEqualsVersion(connection)) {
            List<String> updates = getUpdateSql(dbType);
            for (String sql : updates) {
                JdbcUtils.execute(connection, sql);
            }
        }
    }

    /**
     * 校验表是否存在
     *
     * @param connection 数据库连接
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    private boolean isExistTable(Connection connection) throws SQLException {
        Table table = JdbcUtils.getTableByName(connection, schema, tableName);
        return table != null && tableName.equalsIgnoreCase(table.getTable());
    }

    /**
     * 校验表版本号是否一致
     *
     * @param connection 数据库连接
     * @return 是否一致
     * @throws SQLException SQL异常
     */
    private boolean isEqualsVersion(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(1) FROM PX_CM_TABLE WHERE TABLE_NAME = '" + tableName + "' AND VERSION ='" + version + "'";
        return JdbcUtils.executeCountQuery(connection, sql) > 0;
    }

    private void insertVersion(Connection connection) throws SQLException {
        String sql = "INSERT INTO PX_CM_TABLE(TABLE_NAME,VERSION,FORCE_CREATE) VALUES('" + tableName + "','" + version + "','" + force + "')";
        JdbcUtils.execute(connection, sql);
    }

    private void deleteVersion(Connection connection) throws SQLException {
        String sql = "DELETE FROM PX_CM_TABLE WHERE TABLE_NAME='" + tableName + "'";
        JdbcUtils.execute(connection, sql);
    }

    private void updateVersion(Connection connection) throws SQLException {
        String sql = "UPDATE PX_CM_TABLE SET VERSION ='" + version + "' WHERE TABLE_NAME= '" + tableName + "'";
        JdbcUtils.execute(connection, sql);
    }

    /**
     * 自动建表使用此语句进行删表
     *
     * @param dbType 数据库类型
     * @return 删表SQL
     */
    abstract String getDropSql(String dbType);

    /**
     * 自动建表使用此语句进行建表
     *
     * @param dbType 数据库类型
     * @return 返回建表SQL
     */
    abstract String getCreateSql(String dbType);

    /**
     * 自动建表如果表存在，则进行表修改
     *
     * @param dbType 数据库类型
     * @return 表结构修改语句
     */
    abstract List<String> getUpdateSql(String dbType);

    /**
     * 自动建表使用此语句进行设定主键
     *
     * @param dbType 数据库类型
     * @return 主键执行SQL
     */
    abstract String getPrimaryKeySql(String dbType);

    /**
     * 自动建表使用此语句进行设定索引
     *
     * @param dbType 数据库类型
     * @return 该表所有的索引
     */
    abstract List<String> getIndexSql(String dbType);

    /**
     * 自动建表使用此语句进行设定表注释
     *
     * @param dbType 数据库类型
     * @return 所有的注释SQL
     */
    abstract List<String> getCommentSql(String dbType);

    /**
     * 自动建表使用此语句进行其他表修改
     *
     * @param dbType 数据库类型
     * @return 表修改SQL
     */
    abstract List<String> getOtherSql(String dbType);

    /**
     * 自动建表使用此语句进行数据库初始化
     *
     * @param dbType 数据库类型
     * @return 初始化数据SQL
     */
    abstract List<String> getInitSql(String dbType);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTable that = (AbstractTable) o;
        return Objects.equals(tableName, that.tableName) &&
                Objects.equals(schema, that.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, tableName, schema);
    }
}
