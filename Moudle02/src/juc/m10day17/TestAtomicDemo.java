package juc.m10day17;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TestAtomicDemo
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 10:20
 * @Version 1.0
 **/
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}
class AtomicDemo implements Runnable{
    private AtomicInteger serialNumber = new AtomicInteger();
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}
