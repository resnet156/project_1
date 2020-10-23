package com.bili;

/**
 * 多线程的创建：方式一：继承于Thread类
 * @ClassName ThreadTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/19 19:25
 * @Version 1.0
 **/
public class ThreadTest {
    public static void main(String[] args) {
        //3,创建Thraed类的子类的对象
        MyThread myThread = new MyThread();
        //myThread.setName("1");
        //4,通过此对象调用start方法
        //我们不能通过直接调用run()方法的方式启动一个线程

        myThread.start();//①启动当前线程  ②调用当前线程的run()方法

        for (int i = 0; i < 101; i++) {
            if (i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i + "***********************");
            }
        }
    }
}
//1,创建一个继承于Thread类的子类
class MyThread extends Thread{
    //2,重写Thread类的run方法

    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}