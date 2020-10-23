package com.bili;

/**
 * @ClassName AccountTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 10:11
 * @Version 1.0
 **/
public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();

        Customer c1 = new Customer(account);
        Customer c2 = new Customer(account);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
class Account{
    public double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public synchronized void deposit(double amt){
        if(amt > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance += amt;
            System.out.println(Thread.currentThread().getName() + "存钱    balance:" + balance);
        }
    }
}
class Customer extends Thread{
    private Account acct;
    public Customer(Account acct){
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}
