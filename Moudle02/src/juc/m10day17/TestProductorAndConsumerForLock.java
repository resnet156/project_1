package juc.m10day17;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestProductorAndConsumerForLock
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 17:39
 * @Version 1.0
 **/
public class TestProductorAndConsumerForLock {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor pro = new Productor(clerk);
        Consumer con = new Consumer(clerk);

        new Thread(pro, "生产者A").start();
        new Thread(con, "消费者B").start();

        new Thread(pro, "生产者C").start();
        new Thread(con, "消费者D").start();
    }
}
//店员
class Clerk1{
    private Lock lock = new ReentrantLock();
    private int product = 0;
    private Condition condition = lock.newCondition();
    //进货
    public void get(){
        lock.lock();
        try {
            while (product >= 1){
                System.out.println("产品已满");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    //卖货
    public void sale(){
        lock.lock();
        try {
            while (product <= 0){
                System.out.println("缺货");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
//生产者
class Productor1 implements Runnable{
    private Clerk clerk;

    public Productor1(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}
//消费者
class Consumer1 implements Runnable{
    private Clerk clerk;

    public Consumer1(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
