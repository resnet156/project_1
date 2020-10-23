package com.day21;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName InstantTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 14:21
 * @Version 1.0
 **/
public class InstantTest {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println(instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        long l = instant.toEpochMilli();
        System.out.println(l);

        Instant instant1 = Instant.ofEpochMilli(1598336707805l);
        System.out.println(instant1);

        //DateTimeFormatter 类似于 SimpleDateFormat
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = dateTimeFormatter.format(localDateTime);
        System.out.println(format);
    }
}
