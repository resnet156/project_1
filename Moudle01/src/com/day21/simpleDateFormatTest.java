package com.day21;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName simpleDateFormatTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/24 22:24
 * @Version 1.0
 **/
public class simpleDateFormatTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date date = new Date();
        System.out.println(date.toString());
        //格式化：日期-->字符串
        System.out.println(simpleDateFormat.format(date));

        //解析：字符串 --> 日期
        String s = new String("2022/9/24 下午10:26");
        try {
            Date parse = simpleDateFormat.parse(s);
            System.out.println(parse.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
