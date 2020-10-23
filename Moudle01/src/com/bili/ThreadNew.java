package com.bili;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ThreadNew
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 13:40
 * @Version 1.0
 **/
public class ThreadNew {
    public static void main(String[] args) {
        //3,创建Callanle接口实现类的对象
        NumThread numThread = new NumThread();
        //4,将Callanle接口实现类的对象作为参数传递到FutureTask类的构造器中，并创建FutureTask类对象
        FutureTask futureTask = new FutureTask(numThread);
        //5,将FutureTask类对象作为参数传递到Thread类的构造器中，创建Thread类对象，并调用start()方法
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            //6,获取Callable接口中call()方法的返回值
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
//1,创建一个实现Callable接口的实现类
class NumThread implements Callable{
    //2,实现call()方法，将此线程需要执行的操作声明在此方法中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1 ; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
