package com.bili;

/**
 * @ClassName WWindowWTest1
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/20 14:38
 * @Version 1.0
 **/
public class WWindowWTest1 {
    public static void main(String[] args) {
        WWindoWW1 ww1 = new WWindoWW1();

        Thread t1 = new Thread(ww1);
        Thread t2 = new Thread(ww1);
        Thread t3 = new Thread(ww1);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
class WWindoWW1 implements Runnable{
    private int ticket = 100;
    //Object obj = new Object();
    @Override
    public void run() {
        while (true){
            synchronized(this){
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":卖出的票号为：" + ticket);
                    ticket --;
                }else{
                    break;
                }
            }
        }
    }
}
