package juc.m10day17;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestABCAlternateDemo
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 17:50
 * @Version 1.0
 **/
public class TestABCAlternateDemo {
    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 21; i++) {
                    ad.loopA(i);
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 21; i++) {
                    ad.loopB(i);
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 21; i++) {
                    ad.loopC(i);
                    System.out.println("-------------------------------------------------");
                }
            }
        }, "C").start();
    }
}
class AlternateDemo{
    private int number = 1;

    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void loopA(int totalloop){
        lock.lock();
        try {
            //1，判断
            if (number != 1){
                condition1.await();
            }
            //2,打印
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalloop);
            }
            //3,唤醒
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void loopB(int totalloop){
        lock.lock();
        try {
            //1，判断
            if (number != 2){
                condition2.await();
            }
            //2,打印
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalloop);
            }
            //3,唤醒
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void loopC(int totalloop){
        lock.lock();
        try {
            //1，判断
            if (number != 3){
                condition3.await();
            }
            //2,打印
            for (int i = 1; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalloop);
            }
//            System.out.println("------------------------------------------");
            //3,唤醒
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
