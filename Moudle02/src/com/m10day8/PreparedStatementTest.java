package com.m10day8;

import com.m10day7.User;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * @ClassName PreparedStatementTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/11 21:32
 * @Version 1.0
 **/
public class PreparedStatementTest {

    @Test
    public void testLogin() {
        Scanner scan = new Scanner(System.in);

        System.out.print("用户名： ");
        String userName = scan.nextLine();
        System.out.print("密  码： ");
        String password = scan.nextLine();

        // SELECT user,password FROM user_table WHERE USER = '1' or ' AND PASSWORD = '
        // ='1' or '1' = '1';
        String sql = "select u.`user` user, u.`password` from user_table u where u.`user` = ? and u.`password` = ?;";
        User user = getInstance(User.class, sql, userName, password);
        if (user != null){
            System.out.println("登陆成功!!!");
        }else{
            System.out.println("用户名或密码错误");
        }
    }

    public <T> T getInstance(Class<T> clazz, String sql, Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1,获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2,预编译sql语句,返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i +1, args[i]);
            }

            //3,执行并返回结果集
            rs = ps.executeQuery();

            //4,获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            //5,通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            //6,处理结果集
            if (rs.next()){
                T t = clazz.newInstance();

                //处理结果集一行数据中的每一列数据
                for (int i = 0; i < columnCount; i++) {

                    //获取列值
                    Object columValue = rs.getObject(i + 1);

                    //获取每个列的别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给t对象指定的columLabel属性，赋值为columValue, 通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
}
