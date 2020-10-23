package com.bili;

/**
 * @ClassName ThreadTest1
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/20 11:14
 * @Version 1.0
 **/
public class ThreadTest1 {
    public static void main(String[] args) {
        //3.创建实现类的对象
        MThread mThread = new MThread();
        //4.以实现类作为参数创建Thread类的对象
        Thread t1 = new Thread(mThread);
        //5.通过Thread类的对象调用start()方法
        t1.start();
    }
}

//1.创建一个实现Runnable接口的类
class MThread implements Runnable{
//2.实现run方法
    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            System.out.println(i);
        }
    }
}
