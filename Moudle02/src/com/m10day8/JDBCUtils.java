package com.m10day8;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName JDBCUtils
 * @Description TODO 操作数据库的工具类
 * @Author 李玉龙
 * @Date 2020/10/10 18:13
 * @Version 1.0
 **/
public class JDBCUtils {

    /**
     * @Author 李玉龙
     * @Description //TODO 获取数据库的连接
     * @Date 11:34 2020/10/17
     * @Param []
     * @return java.sql.Connection
     **/

    public static Connection getConnection() throws Exception {
        //获取配置文件中的四个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2,加载驱动
        Class.forName(driverClass);

        //3,获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    /**
     * @Author 李玉龙
     * @Description //TODO 关闭连接和Statement的操作
     * @Date 11:34 2020/10/17
     * @Param [conn, ps]
     * @return void
     **/

    public static void closeResource(Connection conn, Statement ps){
        //关闭
        try {
            if (ps != null){
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * @Author 李玉龙
     * @Description //TODO 关闭资源操作
     * @Date 11:34 2020/10/17
     * @Param [conn, ps, rs]
     * @return void
     **/

    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        //关闭
        try {
            if (ps != null){
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}











