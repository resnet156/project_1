package com.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName JDBCUtils
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/17 11:10
 * @Version 1.0
 **/
public class JDBCUtils {
    /**
     * @Author 李玉龙
     * @Description //TODO 使用c3p0数据库连接池功能
     * @Date 11:12 2020/10/17
     * @Param []
     * @return java.sql.Connection
     **/
    //数据库连接池只需要提供一个就好
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("hellc3p0");
    public static Connection getConnection1() throws SQLException {

        Connection conn = cpds.getConnection();
        return conn;
    }
    /**
     * @Author 李玉龙
     * @Description //TODO 使用DBCP数据库连接池功能
     * @Date 16:57 2020/10/17
     * @Param []
     * @return java.sql.Connection
     **/
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();
            FileInputStream is = new FileInputStream("src/dbcp.properties");
            pros.load(is);
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection2() throws Exception {
        //方式一
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        Properties pros = new Properties();
        //方式二
//        FileInputStream is = new FileInputStream("src/dbcp.properties");
//        pros.load(is);
//        DataSource source = BasicDataSourceFactory.createDataSource(pros);
        Connection conn = source.getConnection();
        return conn;
    }

    /**
     * @Author 李玉龙
     * @Description //TODO 使用Druid数据库连接池技术
     * @Date 17:59 2020/10/17
     * @Param
     * @return
     **/
    private static DataSource source1;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

            pros.load(is);

            source1 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection3() throws Exception {
        Connection conn = source1.getConnection();
        return conn;
    }

    //*************************************************************************************************\\
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
//        try {
//            DbUtils.close(conn);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            DbUtils.close(ps);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            DbUtils.close(rs);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }
}
