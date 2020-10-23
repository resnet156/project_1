package com.m10day8;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @ClassName ExerTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/11 22:17
 * @Version 1.0
 **/
public class ExerTest {

    @Test
    public void testInsert(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入姓名--");
        String name = scanner.next();
        System.out.print("输入邮箱--");
        String email = scanner.next();
        System.out.print("输入生日--");
        String birthday = scanner.next();

        String sql = "insert into customers(name, email, birth) values(?, ?, ?) ";
        int insertCount = update(sql, name, email, birthday);
        if (insertCount > 0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
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
}
