package com.m9day12;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StreamAPITest2
 * @Description TODO
 *
 * 测试Stream的终止操作
 *
 * @Author 李玉龙
 * @Date 2020/9/13 21:20
 * @Version 1.0
 **/
public class StreamAPITest2 {
    //1 - 匹配与查找
    @Test
    public void test1(){
        //allMatch(Predicate p)  检查是否匹配所有元素
        List<Employee> employees = EmployeeData.getEmployees();
        boolean b = employees.stream().allMatch(employee -> employee.getAge() > 18);
        System.out.println(b);

        //anyMatch(Predicate p)  检查是否匹配至少一个元素
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

        //noneMatch(Predicate p)  检查是否没有匹配的元素
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);

        //findFirst   返回第一个元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);

        //findAny  返回任意一个
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);

    }

    @Test
    public void test2(){
        //count()
        List<Employee> employees = EmployeeData.getEmployees();
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);

        //max()
        Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
        Optional<Double> maxSalary = salaryStream.max(Double::compareTo);
        System.out.println(maxSalary);

        //min
        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min);
    }

    //2 - 归约
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        Optional<Double> salarySum = salaryStream.reduce(Double::sum);
        System.out.println(salarySum);
    }

    //3 - 收集
    @Test
    public void test4(){
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employeeList.stream().forEach(System.out::println);

        System.out.println();

        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);

    }
}
