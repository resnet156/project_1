package com.bili;

/**
 * @ClassName ProductTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 11:38
 * @Version 1.0
 **/
public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        producer.setName("生产者");

        Cumtomer cumtomer = new Cumtomer(clerk);
        cumtomer.setName("消费者");

        producer.start();
        cumtomer.start();

    }
}

class Clerk{
    private int productCount = 0;
    public synchronized void producerProduct() {
        if(productCount < 20){
            productCount ++;
            System.out.println(Thread.currentThread().getName() + ":正在生产第" + productCount + "个产品");
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void customerProduct() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + ":正在消费第" + productCount + "个产品");
            productCount --;
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + ":生产者开始生产。。。。。。。。。" );
            clerk.producerProduct();
        }
    }
}

class Cumtomer extends Thread{
    private Clerk clerk;

    public Cumtomer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + ":消费者开始消费。。。。。。。。。" );
            clerk.customerProduct();
        }
    }
}
