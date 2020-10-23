package com.m10day7;

import com.m10day8.JDBCUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName preparedStatemwntTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/9 20:57
 * @Version 1.0
 **/
public class preparedStatemwntTest {

    @Test
    public void testCommonUpdate(){
//        String sql = "delete from customers where id = ?";
//        update(sql, 19);
        String sql = "update `order` set order_name = ? where order_id = ?";
        update(sql, "连横臂", 4);
    }

    //通用的增删改操作
    public void update(String sql, Object ...args) {//sql中的占位符个数应于可变形参的长度相同
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1,获取数据库的连接
            conn = JDBCUtils.getConnection();
            //2,预编译sql语句,返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3,填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //4,执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5,资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
    }
    //修改customers表的一条记录
    @Test
    public void UpdateTest()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1,获取数据库的连接
            conn = JDBCUtils.getConnection();
            //2,预编译sql语句,返回PreparedStatement的实例
            String sql = "update customers set `name` = ? where id = ?";
            ps = conn.prepareStatement(sql);
            //3,填充占位符
            ps.setObject(1, "莫扎特");
            ps.setObject(2, 19);
            //4,执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5,资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
    }

    @Test
    public void test1() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
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
            connection = DriverManager.getConnection(url, user, password);

            //4,预编译sql语句，获得PreparedStatement实例
            String sql = "insert into customers(`name`, email, birth) value(?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            //5,填充占位符
            preparedStatement.setString(1, "哪吒");
            preparedStatement.setString(2, "nezha@qq.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse("1000-01-01");

            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));

            //6,执行
            preparedStatement.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            //7,关闭
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



    }
}












