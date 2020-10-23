package com.m10day8;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * @ClassName ExerTest2
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/13 21:53
 * @Version 1.0
 **/
public class ExerTest2 {
    @Test
    public void test1(){
        String sql = "create table examstudent(" +
                "FlowID int(10) primary key auto_increment," +
                "Type int(5) not null," +
                "IDCard varchar(18) not null," +
                "ExamCard varchar(15) not null," +
                "StudentName varchar(20) not null," +
                "Location varchar(20) not null," +
                "Grade int(10) not null)";
        int update = update(sql);
        if (update > 0){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
    }

    @Test
    public void testInsert(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("四级/六级:");
        int Type = scanner.nextInt();
        System.out.print("身份证号:");
        String IDCard = scanner.next();
        System.out.print("准考证号:");
        String ExamCard = scanner.next();
        System.out.print("学生姓名:");
        String StudentName = scanner.next();
        System.out.print("所在城市:");
        String Location = scanner.next();
        System.out.print("考试成绩:");
        int Grade = scanner.nextInt();

        String sql = "insert into examstudent(Type, IDCard , ExamCard, StudentName, Location, Grade) values(?, ?, ?, ?, ?, ?)";
        int update = update(sql, Type, IDCard, ExamCard, StudentName, Location, Grade);
        if (update > 0){
            System.out.println("插入成功");
        }else {
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

    @Test
    public void test3(){
        System.out.println("请输入您要选择的类型");
        System.out.println("a.准考证号");
        System.out.println("b.身份证号");

        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        if ("a".equalsIgnoreCase(selection)){
            System.out.println("请输入准考证号：");
            String examCard = scanner.next();

            String sql = "select FlowID flowID, Type type, IDCard, ExamCard examCard, StudentName name, Location localtion, Grade grade from examstudent where ExamCard = ?";


            Student student = getInstance(Student.class, sql, examCard);
            if (student != null){
                System.out.println(student);
            }else {
                System.out.println("输入的准考证号有误，请重新输入");
            }


        }else if ("b".equalsIgnoreCase(selection)){
            System.out.println("请输入身份证号：");
            String IDCard = scanner.next();

            String sql = "select FlowID flowID, Type type, IDCard, ExamCard examCard, StudentName name, Location localtion, Grade grade from examstudent where IDCard = ?";


            Student student = getInstance(Student.class, sql, IDCard);
            if (student != null){
                System.out.println(student);
            }else {
                System.out.println("输入的身份证号有误，请重新输入");
            }
        }else{
            System.out.println("输入有误，请重新输入！！！");
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

    @Test
    public void testDelectByExamID(){
        System.out.println("请输入学生的考号");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.next();
        String sql = "select FlowID flowID, Type type, IDCard, ExamCard examCard, StudentName name, Location localtion, Grade grade from examstudent where ExamCard = ?";


        Student student = getInstance(Student.class, sql, examCard);
        if (student == null){
            System.out.println("查无此人，请重新输入");
        }else {
            String sql1 = "delete from examstudent where ExamCard = ?";
            int deleteCount = update(sql1, examCard);
            if (deleteCount > 0){
                System.out.println("删除成功");
            }
        }
    }

    @Test
    public void testDelectByExamID1(){
        System.out.println("请输入学生的考号");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.next();

        String sql = "delete from examstudent where ExamCard = ?";
        int deleteCount = update(sql, examCard);
        if (deleteCount > 0){
            System.out.println("删除成功");
        }else {
            System.out.println("查无此人，请重新输入");
        }
    }
}
