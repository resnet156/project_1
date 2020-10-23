package com.m9day12;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用
 *
 * Created by shkstart.
 */
public class MethodRefTest {

	// 情况一：对象 :: 实例方法
	//Consumer中的void accept(T t)
	//PrintStream中的void println(T t)
	@Test
	public void test1() {
		Consumer<String> con1 = str -> System.out.println(str);
		con1.accept("北京");

		System.out.println("**************************");

		PrintStream ps = System.out;
		Consumer<String> con2 = ps :: println;
		con2.accept("beijing");
		
	}
	
	//Supplier中的T get()
	//Employee中的String getName()
	@Test
	public void test2() {
		Employee emp = new Employee(1001, "Tom", 23, 5000);
		Supplier<String> sup1 = () -> emp.getName();
		System.out.println(sup1.get());

		System.out.println("*************************");

		Supplier<String> sup2 = emp :: getName;
		System.out.println(sup2.get());

	}

	// 情况二：类 :: 静态方法
	//Comparator中的int compare(T t1,T t2)
	//Integer中的int compare(T t1,T t2)
	@Test
	public void test3() {
		Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
		System.out.println(com1.compare(12, 56));

		System.out.println("***************************");

		Comparator<Integer> com2 = Integer::compareTo;
		System.out.println(com2.compare(16, 2));
	}
	
	//Function中的R apply(T t)
	//Math中的Long round(Double d)
	@Test
	public void test4() {
		Function<Double, Long> fun1 = d -> Math.round(d);
		System.out.println(fun1.apply(25.3333));

		System.out.println("**********************");

		Function<Double, Long> fun2 = Math :: round;
		System.out.println(fun2.apply(65.3333));

	}

	// 情况三：类 :: 实例方法 
	// Comparator中的int comapre(T t1,T t2)
	// String中的int t1.compareTo(t2)
	@Test
	public void test5() {
		Comparator<String> com1 = (t1, t2) -> t1.compareTo(t2);
		System.out.println(com1.compare("avs", "iyh"));

		System.out.println("***************************");

		Comparator<String> com2 = String::compareTo;
		System.out.println(com2.compare("ftg", "poi"));
	}

	//BiPredicate中的boolean test(T t1, T t2);
	//String中的boolean t1.equals(t2)
	@Test
	public void test6() {
		BiPredicate<String, String> bi1 = (t1, t2) -> t1.equals(t2);
		System.out.println(bi1.test("jytjfkj", "hgdfjhm"));

		System.out.println("**************************");

		BiPredicate<String, String> bi2 = String::equals;
		System.out.println(bi2.test("GYIY", "UYTF"));
		
	}
	
	// Function中的R apply(T t)
	// Employee中的String getName();
	@Test
	public void test7() {
		Employee tom = new Employee(1001, "Tom", 15, 6666);

		Function<Employee, String> fun1 = e -> e.getName();
		System.out.println(fun1.apply(tom));

		System.out.println("********************");

		Function<Employee, String> fun2 = Employee::getName;
		System.out.println(fun2.apply(tom));
	}

}
