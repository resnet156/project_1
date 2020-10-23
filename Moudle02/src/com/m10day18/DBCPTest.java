package com.m10day18;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName DBCPTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/17 15:38
 * @Version 1.0
 **/
public class DBCPTest {
    /**
     * @Author 李玉龙
     * @Description //TODO 测试DBCP数据库连接池技术
     * @Date 15:41 2020/10/17
     * @Param []
     * @return void
     **/
    //方式一不推荐
    @Test
    public void testGetConnection() throws SQLException {
        //创建了DBCP数据库连接池
        BasicDataSource source = new BasicDataSource();

        //设置基本信息
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test?&serverTimezone=Asia/Shanghai");
        source.setUsername("root");
        source.setPassword("root");

        //还可以设置其他涉及数据库连接池管理的相关属性
        source.setInitialSize(10);
        source.setMaxTotal(10);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }
    //方式二使用配置文件
    @Test
    public void testGetConnection1() throws Exception {
        //方式一
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        Properties pros = new Properties();
        //方式二
        FileInputStream is = new FileInputStream("src/dbcp.properties");
        pros.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(pros);
        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
