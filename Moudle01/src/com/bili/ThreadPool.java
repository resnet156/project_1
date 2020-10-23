package com.bili;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPool
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 14:29
 * @Version 1.0
 **/
public class ThreadPool {
    public static void main(String[] args) {
        //1,提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //设置线程池的属性

        //System.out.println(executorService.getClass());
        //ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        //threadPoolExecutor.setMaximumPoolSize(15);

        //2,执行指定的线程的操作，需要提供实现Runnable接口或Callable接口的实现类的对象
        executorService.execute(new NumberThread());//适用于Runnable接口
        executorService.execute(new NumberThread1());

        //executorService.submit();//适用于Callable接口
        executorService.shutdown();
    }
}
class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

