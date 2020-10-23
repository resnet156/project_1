package juc.m10day17;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName TestCopyOnWriteArrayList
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 15:22
 * @Version 1.0
 **/
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        HelloThread ht = new HelloThread();
        for (int i = 0; i < 2; i++) {
            new Thread(ht).start();
        }
    }
}
class HelloThread implements Runnable{
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            list.add("AA");
        }
    }
}
