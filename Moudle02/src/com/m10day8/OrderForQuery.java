package com.m10day8;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @ClassName OrderForQuery
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/11 11:19
 * @Version 1.0
 **/
public class OrderForQuery {

    @Test
    public void testOrderForQuery(){
        String sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
        Order o1 = orderForQuery1(sql, 2);
        System.out.println(o1);
    }
    public Order orderForQuery1(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1,获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2,预编译sql语句,返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);

            //填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }

            //3,执行并返回结果集
            rs = ps.executeQuery();

            //4,获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            //5,通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            //6,处理结果集
            if (rs.next()){
                Order order = new Order();

                //处理结果集一行数据中的每一列数据
                for(int i = 0;i < columnCount;i++){

                    //获取列值
                    Object columnValue = rs.getObject(i + 1);

                    //获取每个列的别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给order对象指定的columLabel属性，赋值为columValue, 通过反射
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order, columnValue);

                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
    //*************************************************************************************************

    public Order orderForQuery(String sql,Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }

            //执行，获取结果集
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                Order order = new Order();
                for(int i = 0;i < columnCount;i++){
                    //获取每个列的列值:通过ResultSet
                    Object columnValue = rs.getObject(i + 1);
                    //通过ResultSetMetaData
                    //获取列的列名：getColumnName() --不推荐使用
                    //获取列的别名：getColumnLabel()
//					String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //通过反射，将对象指定名columnName的属性赋值为指定的值columnValue
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order, columnValue);
                }

                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            JDBCUtils.closeResource(conn, ps, rs);
        }


        return null;

    }



    //********************************************************************************************************
    @Test
    public void test1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select order_id, order_name, order_date from `order` where order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);
            rs = ps.executeQuery();

            if (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Date date = rs.getDate(3);
                Order order = new Order(id, name, date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
    }
}












