package juc.m10day17;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @ClassName TestForkJoinPool
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/18 21:40
 * @Version 1.0
 **/
public class TestForkJoinPool {
    public static void main(String[] args) {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 50000000000L);//43-1118-5772-5726

        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时为：" + Duration.between(start, end).toMillis());
    }
    @Test
    public void test1(){
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0L; i <= 50000000000L; i++) {//33-3129-15600-15683
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时为：" + Duration.between(start, end).toMillis());
    }
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0L, 50000000000L)//898-3864-3845
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时为：" + Duration.between(start, end).toMillis());
    }
}
class ForkJoinSumCalculate extends RecursiveTask<Long>{
    private long start;
    private long end;
    private static final long THURSHOLD = 10000L;

    private static final long serialVersionUID = 1234453952232125270L;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THURSHOLD){
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();


        }
    }
}
