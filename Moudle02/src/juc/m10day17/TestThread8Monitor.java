package juc.m10day17;

/**
 * @ClassName TestThread8Monitor
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 20:01
 * @Version 1.0
 **/
public class TestThread8Monitor {
    public static void main(String[] args) {

        NumberTest nt = new NumberTest();
        NumberTest nt1 = new NumberTest();


        new Thread(new Runnable() {
            @Override
            public void run() {
                nt.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                nt.getTwo();
                nt1.getTwo();
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                nt.getThree();
//            }
//        }).start();
    }
}
class NumberTest{
    public static synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public static synchronized void getTwo(){
        System.out.println("two");
    }
    public void getThree(){
        System.out.println("three");
    }
}