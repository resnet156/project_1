package com.bili;

/**
 * @ClassName BankTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/20 21:36
 * @Version 1.0
 **/
public class BankTest {
    private BankTest(){

    }
    private  static BankTest instance = null;

    public static BankTest getInstance(){
        if(instance == null){
            synchronized (BankTest.class){
                if(instance == null){
                    instance = new BankTest();
                }
            }
        }
        return instance;
    }
}
