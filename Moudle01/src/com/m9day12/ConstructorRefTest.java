package com.m9day12;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *
 * 二、数组引用
 *
 *
 * Created by shkstart
 */
public class ConstructorRefTest {
	//构造器引用
    //Supplier中的T get()
    @Test
    public void test1(){
        Supplier<Employee> sup1 = () -> new Employee();
        System.out.println(sup1.get());

        System.out.println("************************");

        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());
	}

	//Function中的R apply(T t)
    @Test
    public void test2(){
        Function<Integer, Employee> fun1 = t -> new Employee(t);
        System.out.println(fun1.apply(1001));

        System.out.println("***************************");

        Function<Integer, Employee> fun2 = Employee::new;
        System.out.println(fun2.apply(2001));
	}

	//BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        BiFunction<Integer, String, Employee> bf1 = (t, u) -> new Employee(t, u);
        System.out.println(bf1.apply(1001, "Tom"));

        System.out.println("***************************");

        BiFunction<Integer, String, Employee> bf2 = Employee::new;
        System.out.println(bf2.apply(2001, "Jerry"));
	}

	//数组引用
    //Function中的R apply(T t)
    @Test
    public void test4(){
        Function<Integer, String[]> fun1 = length -> new String[length];
        String[] arr1 = fun1.apply(5);
        System.out.println(Arrays.toString(arr1));

        System.out.println("************************");

        Function<Integer, String[]> fun2 = String[] :: new;
        String[] arr2 = fun2.apply(10);
        System.out.println(Arrays.toString(arr2));
    }
}
