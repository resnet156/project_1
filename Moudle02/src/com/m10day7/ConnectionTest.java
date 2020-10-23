package com.m10day7;

import org.testng.annotations.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * @ClassName ConnectionTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/7 22:10
 * @Version 1.0
 **/
public class ConnectionTest {
    //方式一
    @Test
    public void test1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();

        //jdbc:mysql:  协议
        //localhost:ip  地址
        //3306  默认的mysql的端口号
        //test   test数据库
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();

        info.setProperty("user", "root");
        info.setProperty("password", "root");

        Connection connect = driver.connect(url, info);
        System.out.println(connect);

    }
    //方式二：对方式一的迭代
    @Test
    public void testConnection2() throws Exception {
        //获取java实现类对象：使用反射
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";

        //提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");

        //获取连接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }
    //方式三
    @Test
    public void teatConnection3() throws Exception {
        //1，获取Driver实现类的对象
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //2,提供另外3个链接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";

        //注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    //方式四：只是加载驱动不用显式的注册驱动了
    @Test
    public void testConnection4() throws Exception {

        //1,提供另外3个链接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";
        //2,加载Driver
        Class.forName("com.mysql.jdbc.Driver");
        //Driver driver = (Driver) aClass.newInstance();

        //注册驱动
        //DriverManager.registerDriver(driver);
        /*
        * static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
         */

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    //方式五：将数据库连接需要的4个基本信息声明在配置文件中，通过读取配置文件的方式，获取连接
    /*
    此种方式的好处：
    1，实现了数据与代码的分离，实现了解耦
    2，如果需要修改配置文件信息，可以避免程序重新打包2222

    */
    @Test
    public void testConnection5() throws Exception {
        //获取配置文件中的四个基本信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
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
        System.out.println(connection);

    }
    @Test
    public  void test7(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入: ");//Deditable.java.test.console=true
        String s = scanner.nextLine();
        System.out.println(s + "11111");
    }
}
