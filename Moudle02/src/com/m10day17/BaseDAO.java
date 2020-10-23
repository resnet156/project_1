package com.m10day17;

import com.m10day8.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseDAO
 * @Description TODO DAO:data(base) access object 封装了针对数据表的通用操作
 * @Author 李玉龙
 * @Date 2020/10/15 22:47
 * @Version 1.0
 **/
public abstract class BaseDAO<T> {

    private Class<T> clazz = null;
    //获取当前BaseDAO的子类继承的父类中的泛型
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType)genericSuperclass;

        Type[] typeArguments = paramType.getActualTypeArguments();//获取了父类的泛型
        clazz = (Class<T>)typeArguments[0];//泛型的第一个参数
    }

    //通用的增删改操作(version 考虑上事务)
    public int update(Connection conn, String sql, Object ...args) {//sql中的占位符个数应于可变形参的长度相同
        PreparedStatement ps = null;
        try {
            //1,预编译sql语句,返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2,填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //3,执行  execute(): 如果执行的是查询操作，有返回结果，则返回true
            //如果执行的是增删改操作没有返回值，则返回false
            //ps.execute();
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5,资源的关闭
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }
    //***************************************************************************************\\
    //通用的数据查询操作，用于返回数据表中的一条记录（version 2.0）(考虑上事务)
    public T getInstance(Connection conn, String sql, Object ...args){
        //Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1,获取数据库的连接
            //conn = JDBCUtils.getConnection();

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
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
    //*********************************************************************************************\\
    //通用的数据查询操作，用于返回数据表中的多条记录构成的集合（version 2.0）(考虑上事务)
    public List<T> getForList(Connection conn, String sql, Object ...args){
        //Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1,获取数据库的连接
            //conn = JDBCUtils.getConnection();

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
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
    //  通用的特殊查询操作
    public <E> E getValue(Connection conn, String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            if (rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }

        return null;
    }
}
