package com.day21;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName calenderTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 13:21
 * @Version 1.0
 **/
public class calenderTest {
    public static void main(String[] args) {
        //calender调用其静态方法getInstance()
        Calendar instance = Calendar.getInstance();
        //get()
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));

        //set()
        instance.set(Calendar.DAY_OF_MONTH, 11);
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));

        //add()
        instance.add(Calendar.DAY_OF_MONTH, 3);
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));

        //getTime() 日历类 --> Date
        Date date = instance.getTime();
        System.out.println(date);

        //setTime()  Date --> 日历类
        Date date1 = new Date();
        instance.setTime(date1);
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));
    }
}
