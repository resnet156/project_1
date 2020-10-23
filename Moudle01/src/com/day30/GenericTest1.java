package com.day30;

import org.junit.Test;

/**
 * @ClassName GenericTest1
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 16:06
 * @Version 1.0
 **/
public class GenericTest1 {
    @Test
    public void test1(){
        Order<String> order = new Order<>("Tom", 22, "just so so");

    }
    @Test
    public void test2(){
        OrderTest orderTest = new OrderTest();
        orderTest.setOrderT(45);
        Order<String> stringOrder = new Order<>();
        stringOrder.setOrderT("45");
    }
}
