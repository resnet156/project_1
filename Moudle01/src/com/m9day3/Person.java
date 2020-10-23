package com.m9day3;

import java.io.Serializable;

/**
 * @ClassName Person
 * @Description TODO
 * Person类需要实现如下的要求方可序列化
 * 1,需要实现接口：Serializable
 * 2,当前类提供一个全局常量：serialVersionUID
 * 3,除了当前Person类需要实现Serializable接口外，必须保证其内部所有属性也是可序列化的（默认情况下基本数据类型都是可序列化的）
 *
 * @Author 李玉龙
 * @Date 2020/9/6 10:38
 * @Version 1.0
 **/
public class Person implements Serializable {
    public static final long serialVersionUID = 414254555L;
    private String name;
    private int age;
    private Account acut;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Account acut) {
        this.name = name;
        this.age = age;
        this.acut = acut;
    }

    public Account getAcut() {
        return acut;
    }

    public void setAcut(Account acut) {
        this.acut = acut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", acut=" + acut +
                '}';
    }
}
class Account implements Serializable{

    private static final long serialVersionUID = 4254756634435385L;

    private double balance;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
