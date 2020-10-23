package juc.m10day17;

/**
 * @ClassName TestCompareAndSwap
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 11:00
 * @Version 1.0
 **/
public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.get();
                    boolean b = cas.CompareAndSet(expectedValue, (int) (Math.random() * 101));
                    System.out.println(b);
                }
            }).start();
        }
    }
}
class CompareAndSwap{
    private int value;

    //获取内存值
    public synchronized int get(){
        return value;
    }

    //比较
    public synchronized int CompareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            oldValue = newValue;
        }
        return oldValue;
    }

    //设置
    public synchronized boolean CompareAndSet(int expectedValue, int newValue){
        return newValue == CompareAndSwap(expectedValue, newValue);
    }


}
