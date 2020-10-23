package com.bili;

/**
 * @ClassName WWindowWTest4
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 8:24
 * @Version 1.0
 **/
public class WWindowWTest4 {
    public static void main(String[] args) {
        WWindoWW4 ww4 = new WWindoWW4();

        Thread t1 = new Thread(ww4);
        Thread t2 = new Thread(ww4);
        Thread t3 = new Thread(ww4);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
class WWindoWW4 implements Runnable{
    private int ticket = 100;
    //Object obj = new Object();
    @Override
    public void run() {
        while (true){
            show();
        }
    }

    private synchronized void show(){//此时同步锁为
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":卖出的票号为：" + ticket);
            ticket --;
        }
    }
}

