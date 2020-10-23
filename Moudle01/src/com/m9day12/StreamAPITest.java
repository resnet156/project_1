package com.m9day12;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName StreamAPITest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/13 17:01
 * @Version 1.0
 **/
public class StreamAPITest {
    //通过集合创建Stream
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //顺序流
        Stream<Employee> stream = employees.stream();
        //并行流
        Stream<Employee> parallelStream = employees.parallelStream();

    }

    //通过数组创建Stream
    @Test
    public void test2(){
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        IntStream stream = Arrays.stream(arr1);

        Employee e1 = new Employee(1001, "Tom");
        Employee e2 = new Employee(1002, "Jerry");
        Employee[] employees = {e1, e2};
        Stream<Employee> stream1 = Arrays.stream(employees);

    }

    //通过Stream的of()方法创建Stream
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

    }

    //创建无限流
    @Test
    public void test4(){
        //迭代
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        System.out.println("**********************");
        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
