package com.bili;

/**
 * @ClassName CommucationTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 11:06
 * @Version 1.0
 **/
public class CommucationTest {
    public static void main(String[] args) {
        Number n1 = new Number();

        Thread t1 = new Thread(n1);
        Thread t2 = new Thread(n1);

        t1.start();
        t2.start();
    }
}
class Number implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                notify();
                if(num <= 100){
                    try {
                        Thread.sleep(10);//sleep()可以导致阻塞但不会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num ++;

                    try {
                        wait();//wait()可以释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }

    }
}
