package com.bili;

/**
 * @ClassName WWindowWTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/20 11:00
 * @Version 1.0
 **/
public class WWindowWTest {
    public static void main(String[] args) {
        WWindoWW ww1 = new WWindoWW();
        WWindoWW ww2 = new WWindoWW();
        WWindoWW ww3 = new WWindoWW();

        ww1.setName("窗口一");
        ww2.setName("窗口二");
        ww3.setName("窗口三");

        ww1.start();
        ww2.start();
        ww3.start();
    }
}
class WWindoWW extends Thread{
    private static int ticket = 100;
    @Override
    public void run() {
        while (true){
            if(ticket > 0){
                System.out.println(getName() + ":卖出的票号为" + ticket);
                ticket --;
            }
        }
    }
}
