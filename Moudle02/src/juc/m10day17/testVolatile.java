package juc.m10day17;

/**
 * @ClassName testVolatile
 * @Description TODO volatile关键字：当多个线程操作共享数据时，可以保证内存中的数据可见
 *                                  相较于synchronized是一种较于轻量级的锁
 *                                  1,volatile  不具备‘互斥性’
 *                                  2,volatile  不能保证变量的原子性
 * @Author 李玉龙
 * @Date 2020/10/17 22:53
 * @Version 1.0
 **/
public class testVolatile {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true){
                if (td.isFlag()){
                    System.out.println("------------------------");
                    break;
                }
        }
    }
}
class ThreadDemo implements Runnable{
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + isFlag());
    }
}