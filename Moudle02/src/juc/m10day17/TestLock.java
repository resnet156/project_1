package juc.m10day17;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestLock
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 16:35
 * @Version 1.0
 **/
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "1号").start();
        new Thread(ticket, "2号").start();
        new Thread(ticket, "3号").start();
    }
}
class Ticket implements Runnable{
    private int ticket = 100;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {

        while (true){
            lock.lock();
            try {
                if (ticket > 0){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "窗口余票为：" + (--ticket));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}