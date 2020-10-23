package juc.m10day17;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName TestReadWriteLock
 * @Description TODO  ReadWriteLock  写写， 写读，都互斥    读读不互斥
 * @Author 李玉龙
 * @Date 2020/10/18 19:39
 * @Version 1.0
 **/
public class TestReadWriteLock {
    public static void main(String[] args) {

        ReadWriteLockDemo rwl = new ReadWriteLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                rwl.set((int)(Math.random() * 101));
            }
        }, "Write:").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rwl.get();
                }
            }, "Read" + i).start();
        }
    }
}
class ReadWriteLockDemo{
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //读
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + number);
        } finally {
            lock.readLock().unlock();
        }
    }
    //写
    public void set(int number){
        lock.writeLock().lock();
        try {
            this.number = number;
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.writeLock().unlock();
        }
    }
}
