package com.m10day8;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @ClassName CustomerForQurry
 * @Description TODO 针对于customers表的查询操作
 * @Author 李玉龙
 * @Date 2020/10/10 19:43
 * @Version 1.0
 **/
public class CustomerForQurry {

    @Test
    public void testqueryForCustomers(){
        String sql = "select id,`name`,birth,email from customers where id = ?";
        Customer customer = queryForCustomers(sql, 13);
        System.out.println(customer);

        sql = "select `name`, email from customers where `name` = ?";
        Customer customer1 = queryForCustomers(sql, "周杰伦");
        System.out.println(customer1);

//        String sql = "select name,email from customers where name = ?";
//        Customer customer1 = queryForCustomers(sql,"周杰伦");
//        System.out.println(customer1);
    }

    /*
     * @Author 李玉龙
     * @Description //TODO 针对于customers表的通用的查询操作
     * @Date 20:50 2020/10/10
     * @Param
     * @return
     **/

    public Customer queryForCustomers(String sql,Object...args){
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
            if(rs.next()){
                Customer cust = new Customer();
                //处理结果集一行数据中的每一列数据
                for(int i = 0;i <columnCount;i++){
                    //获取列值
                    Object columValue = rs.getObject(i + 1);

                    //获取每个列的列名
                    String columName = rsmd.getColumnName(i + 1);
                    //String columnName = rsmd.getColumnName(i + 1);

                    //给cust对象指定的columName属性，赋值为columValue, 通过反射
                    Field field = Customer.class.getDeclaredField(columName);
                    field.setAccessible(true);
                    field.set(cust, columValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
    //***********************************************************************************************************
    public Customer queryForCustomers1(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            //获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if(rs.next()){
                Customer cust = new Customer();
                //处理结果集一行数据中的每一个列
                for(int i = 0;i <columnCount;i++){
                    //获取列值
                    Object columValue = rs.getObject(i + 1);

                    //获取每个列的列名
//					String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust, columValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.closeResource(conn, ps, rs);

        }

        return null;


    }




    //***************************************************************************************************************
    @Test
    public void testQurry1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1,获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2,预编译sql语句,返回PreparedStatement的实例
            String sql = "select id, `name`, email, birth from customers where id = ?";
            ps = conn.prepareStatement(sql);

            //填充占位符
            ps.setObject(1, 1);

            //3,执行并返回结果集
            resultSet = ps.executeQuery();

            //4,处理结果集
            if (resultSet.next()){//next(): 判断结果集的下一条是否有数据，如果有数据返回true，并指针下移，如果没有数据返回false,指针不会下移

                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5,关闭资源
            JDBCUtils.closeResource(conn, ps, resultSet);
        }
    }
}
