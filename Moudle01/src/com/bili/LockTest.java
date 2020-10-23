package com.bili;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式3:Lock锁  ,,,jdk5.0新增
 * @ClassName LockTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 8:48
 * @Version 1.0
 **/
public class LockTest {
    public static void main(String[] args) {
        Pindow pindow = new Pindow();

        Thread t1 = new Thread(pindow);
        Thread t2 = new Thread(pindow);
        Thread t3 = new Thread(pindow);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
class Pindow implements Runnable{
    private int ticket = 100;
    //1,实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                //2,调用锁定方法lock()
                lock.lock();
                if(ticket > 0){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":卖出的票号为：" + ticket);
                    ticket --;
                }else{
                    break;
                }
            }finally {
                //3,调用解锁方法unlock()
                lock.unlock();
            }
        }
    }
}
