package com.bili;

/**
 * @ClassName WWindowWTest3
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/20 18:21
 * @Version 1.0
 **/
public class WWindowWTest3 {
    public static void main(String[] args) {
        WWindoWW3 ww1 = new WWindoWW3();
        WWindoWW3 ww2 = new WWindoWW3();
        WWindoWW3 ww3 = new WWindoWW3();

        ww1.setName("窗口一");
        ww2.setName("窗口二");
        ww3.setName("窗口三");

        ww1.start();
        ww2.start();
        ww3.start();
    }
}
class WWindoWW3 extends Thread{
    private static int ticket = 100;
    private static Object obj = new Object();
    @Override
    public void run() {
        while (true){
            //synchronized (obj){
            synchronized (WWindoWW3.class){
                if(ticket > 0){
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + ":卖出的票号为" + ticket);
                    ticket --;
                }else{
                    break;
                }
            }
        }
    }
}
