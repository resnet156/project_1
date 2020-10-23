package com.day30;

import java.util.List;
import java.util.Scanner;

/**
 * @ClassName HappyTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 16:19
 * @Version 1.0
 **/
public class HappyTest {
    public static void main(String[] args) {
        int p = 0;
        boolean flag = true;
        Happy happy = new Happy();
        happy.enter1();
        happy.start();
        List<String> list = happy.wordBook();

        while (flag){
            happy.enter2();
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if(s.length() > 0){
                happy.enter1();
                System.out.println(list.get(p));
                p ++;
            }else{
                happy.enter1();
                happy.sb();
            }
            if (p > 20){
                flag = false;
                happy.enter1();
                happy.end();
            }
        }
    }
}
