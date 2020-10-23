package com.m9day12;

import org.junit.Test;

import java.util.Comparator;

/**
 * @ClassName LambdaTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/12 16:47
 * @Version 1.0
 **/
public class LambdaTest {
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();

        System.out.println("***************************");

        Runnable r2 = () -> System.out.println("我爱北京故宫");
        r2.run();

    }
    @Test
    public void test2(){
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = comp.compare(12, 65);
        System.out.println(compare);
        System.out.println("**********************************");

        //lambda表达式的写法
        Comparator<Integer> comp2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare1 = comp.compare(100, 65);
        System.out.println(compare1);

        //方法引用
        Comparator<Integer> comp3 = Integer :: compare;
        int compare3 = comp.compare(100, 65);
        System.out.println(compare1);
    }
}
