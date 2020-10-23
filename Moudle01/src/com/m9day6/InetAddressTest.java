package com.m9day6;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName InetAddressTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/6 17:23
 * @Version 1.0
 **/
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("196.168.11.22");
            System.out.println(inet1);
            InetAddress inet2 = InetAddress.getByName("www.atguigu.com");
            System.out.println(inet2);
            InetAddress inet3 = InetAddress.getLocalHost();
            System.out.println(inet3);
            InetAddress inet4 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet4);
            System.out.println(inet2.getHostName());
            System.out.println(inet2.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
