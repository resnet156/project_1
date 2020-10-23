package juc.m10day17;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName TestCountDownLatch
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 15:42
 * @Version 1.0
 **/
public class TestCountDownLatch {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(ld).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));

    }
}
class LatchDemo implements Runnable{
    private CountDownLatch latch;
    public LatchDemo(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        synchronized (this){
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0){
                        System.out.println(i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }
}
