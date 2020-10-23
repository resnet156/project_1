package com.day21;

/**
 * @ClassName OtherClassTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 18:43
 * @Version 1.0
 **/
public class OtherClassTest {
    public static void main(String[] args) {
        //System
        String javaVersion = System.getProperty("java.version");
        System.out.println("javaVersion: " + javaVersion);

        String javaHome = System.getProperty("java.home");
        System.out.println("javaHome: " + javaHome);

        String osName = System.getProperty("os.name");
        System.out.println("osName: " + osName);

        String osVersion = System.getProperty("os.version");
        System.out.println("osVersion: " + osVersion);

        String userName = System.getProperty("user.name");
        System.out.println("userName: " + userName);

        String userHome = System.getProperty("user.home");
        System.out.println("userHome: " + userHome);

        String userDir = System.getProperty("user.dir");
        System.out.println("userDir: " + userDir);
    }
}
