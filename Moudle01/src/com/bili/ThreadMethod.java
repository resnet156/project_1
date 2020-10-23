package com.bili;

/**
 * 测试Thread中的方法
 * 1.start()  启动当前线程， 调用当前线程的run()方法
 * 2.run()  通常需要重写Thread中的此方法， 将创建的线程中要执行的操作声明在此方法中
 * 3.currentThread()静态方法  返回执行当前代码的对象
 * 4.getName()获得当前线程的名字
 * 5.setName()设置当前线程的名字
 * 6.yield()  释放当前cpu的执行权
 * 7. join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态。
 * 8. stop():已过时。当执行此方法时，强制结束当前线程。
 * 9. sleep(long millitime):让当前线程“睡眠”指定的millitime毫秒。在指定的millitime毫秒时间内，当前线程是阻塞状态。
 * 10. isAlive():判断当前线程是否存活
 *
 * @ClassName ThreadMethod
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/19 22:48
 * @Version 1.0
 **/
public class ThreadMethod {
    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();
        helloThread.setName("线程一");
        helloThread.start();

        HelloThread helloThread1 = new HelloThread("线程二");
        helloThread1.start();

        Thread.currentThread().setName("主线程");
        for (int i = 0; i < 101; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            if(i % 20 == 0){
                try {
                    helloThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class HelloThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            if(i % 2 == 0){
                try {
                    this.join(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
/*            if (i % 20 == 0){
                this.yield();
            }*/
        }
    }
    public HelloThread(String name){
        super(name);
    }

    public  HelloThread(){

    }
}
