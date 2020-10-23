package com.m10day17;

import com.m10day15.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @InterfaceName CustomerDAO
 * @Description TODO  此接口用于规范对customers表的常用操作
 * @Author 李玉龙
 * @Date 2020/10/16 10:33
 * @Version 1.0
 **/
public interface CustomerDAO {

    /**
     * @Author 李玉龙
     * @Description //TODO 将cust对象添加到数据库中
     * @Date 10:47 2020/10/16
     * @Param [conn, cust]
     * @return void
     **/
    void insert(Connection conn, Customer cust);

    /**
     * @Author 李玉龙
     * @Description //TODO 针对指定的id，删除表中的一条记录
     * @Date 10:47 2020/10/16
     * @Param [conn, id]
     * @return void
     **/
    void deleteById(Connection conn, int id);

    /**
     * @Author 李玉龙
     * @Description //TODO 针对内存中的cust对象，去修改数据表中指定的记录
     * @Date 10:46 2020/10/16
     * @Param [conn, cust]
     * @return void
     **/
    void update(Connection conn, Customer cust);

    /**
     * @Author 李玉龙
     * @Description //TODO 针对指定的id查询得到Customer对象
     * @Date 11:31 2020/10/16
     * @Param [conn, id]
     * @return com.m10day15.Customer
     **/
    Customer getCustomerById(Connection conn, int id);

    /**
     * @Author 李玉龙
     * @Description //TODO 查询表中所有记录构成的集合
     * @Date 10:52 2020/10/16
     * @Param [conn]
     * @return java.util.List<com.m10day15.Customer>
     **/
    List<Customer> getAll(Connection conn);
    
    /**
     * @Author 李玉龙
     * @Description //TODO 返回数据表中的数据的条数目
     * @Date 10:54 2020/10/16
     * @Param [conn]
     * @return java.lang.Long
     **/
    Long getCount(Connection conn);

    /**
     * @Author 李玉龙
     * @Description //TODO 返回数据表中最大的生日
     * @Date 10:59 2020/10/16
     * @Param [conn]
     * @return java.sql.Date
     **/
    Date getMaxBirth(Connection conn);
}
