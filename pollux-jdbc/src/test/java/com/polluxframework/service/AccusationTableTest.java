package com.polluxframework.service;

import com.polluxframework.util.JdbcUtils;
import com.polluxframework.exception.BaseException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;


/**
 * @author zhumin0508
 * created in  2018/8/10 17:09
 * modified By:
 */
public class AccusationTableTest {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://192.168.65.152:3306/station_1?useUnicode=true&characterEncoding=utf8";
    private String username = "root";
    private String password = "123";

    @Test
    public void execute() {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection(driver, url, username, password);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("获取链接失败", connection);
        new AccusationTable().execute(connection);
    }
}