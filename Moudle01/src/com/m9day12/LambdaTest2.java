package com.m9day12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @ClassName LambdaTest2
 * @Description TODO
 *  *
 *  * java 内置的四大核心函数式接口：
 *  *
 *  * 消费型接口：Consumer<T>      void accept(T t)
 *  * 供给型接口：Supplier<T>      T get()
 *  * 函数型接口：Function<T, R>   R apply(T t)
 *  * 断定型接口：Predicate<T>     boolean test(T t)
 *  *
 *  *
 *  *
 *  *
 * @Author 李玉龙
 * @Date 2020/9/12 19:51
 * @Version 1.0
 **/
public class LambdaTest2 {
    @Test
    public void test1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累了， 去天上人间买了一瓶矿泉水，价格为：" + aDouble);
            }
        });

        System.out.println("***********************************");

        happyTime(500, money -> System.out.println("学习太累了， 去天上人间买了一瓶矿泉水，价格为：" + money));
    }

    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void test2(){
        List<String> list = Arrays.asList("北京", "南京", "天津", "东京", "上海", "普京");
        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterStrs);

        System.out.println("*********************************");

        List<String> list1 = filterString(list, s -> s.contains("京"));
        System.out.println(list1);
    }

    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();

        for (String s : list){
            if (pre.test(s)){
                filterList.add(s);
            }
        }

        return filterList;
    }
}
