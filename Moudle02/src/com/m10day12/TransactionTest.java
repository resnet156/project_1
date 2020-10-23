package com.m10day12;

import com.m10day8.JDBCUtils;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @ClassName TransactionTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/15 17:20
 * @Version 1.0
 **/
public class TransactionTest {
    /*
     * @Author 李玉龙
     * @Description //TODO
     *                 针对数据表user_table来说
     *                 AA用户给BB用户转账100
     *
     * @Date 17:23 2020/10/15
     * @Param
     * @return
     **/

    @Test
    public void testUpdate(){
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");

        System.out.println("转账成功");
    }
//*******************************未考虑数据库事务前的转账操作*****************************************************************\\
    @Test
    public void testUpdateWithTx() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getAutoCommit());
            //1,取消数据的自动提交
            conn.setAutoCommit(false);

            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn, sql1, "AA");

            //模拟网络异常
            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn, sql2, "BB");
            //2,提交数据
            conn.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
            //3,回滚数据
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    //通用的增删改操作
    public int update(String sql, Object ...args) {//sql中的占位符个数应于可变形参的长度相同
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
            //4,执行  execute(): 如果执行的是查询操作，有返回结果，则返回true
            //如果执行的是增删改操作没有返回值，则返回false
            //ps.execute();
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5,资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }

    //*******************************考虑数据库事务后的转账操作*****************************************************************\\
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
    //**************************************************************************************************************
    @Test
    public void testTransactionSelect() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        //获取当前连接的隔离级别
        System.out.println(conn.getTransactionIsolation());
        //设置数据库的隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
        //取消自动提交数据
        conn.setAutoCommit(false);

        String sql = "select `user`, `password`, balance from user_table where `user` = ?";
        User cc = getInstance(conn, User.class, sql, "CC");
        System.out.println(cc);

    }

    @Test
    public void testTranssctionUpdate() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        //获取当前连接的隔离级别
        System.out.println(conn.getTransactionIsolation());
        //设置数据库的隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
        //取消自动提交数据
        conn.setAutoCommit(false);
        String sql = "update user_table set balance = ? where `user` = ?";
        int cc = update(conn, sql, 5000, "CC");

        Thread.sleep(15000);
        System.out.println("修改结束");
    }


    //通用的数据查询操作，用于返回数据表中的一条记录（version 2.0）(考虑上事务)
    public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object ...args){
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
}
