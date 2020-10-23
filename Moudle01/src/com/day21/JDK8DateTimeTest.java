package com.day21;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @ClassName JDK8DateTimeTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 13:42
 * @Version 1.0
 **/
public class JDK8DateTimeTest {
    public static void main(String[] args) {
        //now() 获取当前的日期 或时间 或日期和时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of  设置指定的年、 月、 日、 时、 分、 秒  没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 8, 25, 13, 49, 58);
        System.out.println(localDateTime1);

        //getXxx() 获取相关的属性
        System.out.println(localDateTime.getDayOfYear());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());

        //体现了不可变性
        //withXxx()  设置相关属性
        LocalDateTime localDateTime2 = localDateTime1.withDayOfYear(225);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);

        //plusXxx()  增加
        //minusXxx()  减少


    }
}
