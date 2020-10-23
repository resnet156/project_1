package com.m10day12;

import com.m10day8.JDBCUtils;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName InsertTest
 * @Description TODO 使用PreparedStatement实现批量数据的操作
 *                      update和delete本身就具有批量操作的效果
 *                      此时的批量操作，主要指的是批量插入，使用PreparedStatement如何实现更高效的批量插入
 * @Author 李玉龙
 * @Date 2020/10/15 13:05
 * @Version 1.0
 **/
public class InsertTest {
    @Test
    public void InsertTest1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(`name`) values (?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {
                ps.setObject(1, "name_" + i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    @Test
    public void InsertTest2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(`name`) values (?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);

                //1,赞数据
                ps.addBatch();

                if (i % 500 == 0){
                    //2,执行
                    ps.executeBatch();

                    //3,清空
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    @Test
    public void InsertTest3() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            //设置不允许自动提交
            conn.setAutoCommit(false);

            String sql = "insert into goods(`name`) values (?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);

                //1,赞数据
                ps.addBatch();

                if (i % 500 == 0){
                    //2,执行
                    ps.executeBatch();

                    //3,清空
                    ps.clearBatch();
                }
            }
            //提交数据
            conn.commit();

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
