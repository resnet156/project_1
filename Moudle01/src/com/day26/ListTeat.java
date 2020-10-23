package com.day26;

/**
 * @ClassName ListTeat
 * @Description //List接口 存储有序的可重复的数据
 * //比较 ArrayList、LinkedList、 Vector 三者的异同
 *三个类都实现了List接口， 存储数据的特点相同：有序可重复
 * ArrayList实现类： 作为List接口最主要的实现类：线程不安全，效率高 底层使用Object[] elementDate存储
 * **建议开发中使用带参的构造器  ArrayList arr = new ArrayList(int capacity)
 * jdk7中的ArrayList的对象的创建类似于单例的饿汉式，而jdk8中的ArrayList的对象的创建类似于单例的懒汉式，延迟了数组的创建，节省内存.
 * LinkedList实现类：对于插入删除操作，使用此类效率比ArrayList效率高,底层使用双向链表存储
 * Vector实现类：作为List接口最古老的实现类：线程安全，效率低  底层使用Object[] elementDate存储
 *
 *
 * @Author 李玉龙
 * @Date 2020/8/26 20:48
 * @Version 1.0
 **/
public class ListTeat {
    public static void main(String[] args) {

    }
}
