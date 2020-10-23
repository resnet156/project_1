package com.dbutils;

import com.m10day15.Customer;
import com.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QueryRunnerTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/17 18:15
 * @Version 1.0
 **/
public class QueryRunnerTest {
    //commons-dbutils 是 Apache 组织提供的一个开源 JDBC工具类库，它是对JDBC的简单封装，
    // 学习成本极低，并且使用dbutils能极大简化jdbc编码的工作量，同时也不会影响程序的性能。

    //测试插入
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "insert into customers(`name`, email, birth) values(?, ?, ?)";
            int insertCount = runner.update(conn, sql, "王浩", "wang@qq.com", "2000-07-07");
            System.out.println("添加了:" + insertCount + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    //测试查询
    /**
     * @Author 李玉龙
     * @Description //TODO BeanHandler是ResultSetHandler接口的实现类，用于封装表中的一条记录
     * @Date 18:44 2020/10/17
     * @Param []
     * @return void
     **/
    
    @Test
    public void testQuery() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id, `name`, email, birth from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(conn, sql, handler, 23);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    /**
     * @Author 李玉龙
     * @Description //TODO BeanListHandler是ResultSetHandler接口的实现类，用于封装表中的多条记录构成的集合
     * @Date 19:38 2020/10/17
     * @Param []
     * @return void
     **/

    @Test
    public void testQuery1() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id, `name`, email, birth from customers where id < ?";
            BeanListHandler<Customer> listHandler = new BeanListHandler<>(Customer.class);
            List<Customer> customerList = runner.query(conn, sql, listHandler, 23);
            customerList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    //测试查询
    /**
     * @Author 李玉龙
     * @Description //TODO MapHandler是ResultSetHandler接口的实现类，对应于表中的一条记录,
     *                      将字段及相应字段的值作为map中的key和value
     * @Date 18:44 2020/10/17
     * @Param []
     * @return void
     **/

    @Test
    public void testQuery3() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id, `name`, email, birth from customers where id = ?";
            MapHandler mapHandler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, mapHandler, 22);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    /**
     * @Author 李玉龙
     * @Description //TODO MapHandler是ResultSetHandler接口的实现类，对应于表中的多条记录,
     *      *                      将字段及相应字段的值作为map中的key和value,将这些map添加到List
     * @Date 19:38 2020/10/17
     * @Param []
     * @return void
     **/

    @Test
    public void testQuery4() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id, `name`, email, birth from customers where id < ?";
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> mapList = runner.query(conn, sql, mapListHandler, 23);
            mapList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testQuery5() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select count(*) from customers";
            ScalarHandler scalarHandler = new ScalarHandler();
            Long queryCount = (Long) runner.query(conn, sql, scalarHandler);
            System.out.println(queryCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    @Test
    public void testQuery6() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select MAX(birth) from customers";
            ScalarHandler scalarHandler = new ScalarHandler();
            Object query = runner.query(conn, sql, scalarHandler);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}
