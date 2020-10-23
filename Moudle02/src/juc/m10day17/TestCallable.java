package juc.m10day17;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName TestCallable
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 16:06
 * @Version 1.0
 **/
public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo1 td = new ThreadDemo1();
        FutureTask<Integer> result= new FutureTask<>(td);
        new Thread(result).start();

        try {
//            System.out.println("-----------------");
            Integer sum = result.get();
            System.out.println("---------------");
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class ThreadDemo1 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100000000; i++) {
            sum+=i;
        }
        return sum;
    }
}
