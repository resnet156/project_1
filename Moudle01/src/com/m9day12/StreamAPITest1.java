package com.m9day12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName StreamAPITest1
 * @Description TODO
 *
 * 测试Stream的中间操作
 *
 * @Author 李玉龙
 * @Date 2020/9/13 17:43
 * @Version 1.0
 **/
public class StreamAPITest1 {
    //1 - 筛选与切片
    @Test
    public void test1(){
        //filter(Predicate p)   从流中排除某些元素
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Employee> stream = list.stream();

        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        System.out.println();

        //limit  - 截断流， 使其元素不超过给定数量
        list.stream().limit(5).forEach(System.out::println);

        System.out.println();

        //skip(n)  跳过指定数量
        list.stream().skip(5).forEach(System.out::println);

        System.out.println();

        //distinct()
        list.add(new Employee(1010, "刘强东", 40, 5000));
        list.add(new Employee(1010, "刘强东", 40, 5000));
        list.add(new Employee(1010, "刘强东", 40, 5000));
        list.add(new Employee(1010, "刘强东", 40, 5000));
        list.add(new Employee(1010, "刘强东", 40, 5000));

        list.stream().distinct().forEach(System.out::println);
    }

    //映射
    @Test
    public void test2(){
        //map(Function f) 接受一个元素作为参数， 将元素转换成其他形式或者提取信息
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> stringStream = employees.stream().map(Employee::getName);

        stringStream.filter(str -> str.length() > 3).forEach(System.out::println);

        System.out.println();

        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });

        System.out.println();

        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);

    }
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    //3 -- 排序
    @Test
    public void test3(){
        //sorted()   自然排序
        List<Integer> list = Arrays.asList(41, 56, 32, 52, -5, 76, 39, 91, 0);
        list.stream().sorted().forEach(System.out::println);

        System.out.println();

        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1, e2) -> {
            int intValue = Integer.compare(e1.getAge(), e2.getAge());
            if (intValue != 0){
                return intValue;
            }else {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

        System.out.println();


    }
}
