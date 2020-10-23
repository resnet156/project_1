package com.m10day8;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PreparedStatementQueryTest
 * @Description TODO 通过PreparedStatement实现对不同表的通用查询操作
 * @Author 李玉龙
 * @Date 2020/10/11 12:29
 * @Version 1.0
 **/
public class PreparedStatementQueryTest {

    @Test
    public void testGetForList(){
        String sql = "select id, `name`, email, birth from customers where id < ?";
        List<Customer> customerList = getForList(Customer.class, sql, 13);
        customerList.forEach(System.out::println);

        System.out.println();

        sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id < ?";
        List<Order> orderList = getForList(Order.class, sql, 5);
        orderList.forEach(System.out::println);
    }

    public <T> List<T> getForList(Class<T> clazz, String sql, Object ...args){
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

            //创建集合
            ArrayList<T> list = new ArrayList<>();

            //6,处理结果集
            while (rs.next()){
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
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    @Test
    public void testGetInstance(){
        String sql = "select id, `name`, email, birth from customers where id = ?";
        Customer customer = getInstance(Customer.class, sql, 1);
        System.out.println(customer);

        sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql, 1);
        System.out.println(order);
    }

    /*
     * @Author 李玉龙
     * @Description //TODO 通过PreparedStatement实现对不同表的通用查询操作 返回一条
     * @Date 14:06 2020/10/11
     * @Param
     * @return
     **/

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
