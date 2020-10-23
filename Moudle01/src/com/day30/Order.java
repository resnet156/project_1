package com.day30;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Order
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 15:53
 * @Version 1.0
 **/
public class Order <T>{
    String orderName;
    int orderId;

    T orderT;
    public Order(){};

    public Order(String orderName, int orderId, T orderT){
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public String getOrderName() {
        return orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    public <E>List<E> copyFormArrayList(E[] list){
        ArrayList<E> list1 = new ArrayList<>();
        for (E e : list){
            list1.add(e);
        }
        return list1;
    }
}
