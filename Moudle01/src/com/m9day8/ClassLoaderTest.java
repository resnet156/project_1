package com.m9day8;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName ClassLoaderTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/8 19:50
 * @Version 1.0
 **/
public class ClassLoaderTest {
    @Test
    public void test1(){
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader1);

        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);
    }
    @Test
    public void test2() throws IOException {


            Properties properties = new Properties();
            //fis = new FileInputStream("jdbc.properties");

            ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
            InputStream is = classLoader1.getResourceAsStream("jdbc1.properties");

            properties.load(is);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            System.out.println("user = " + user + "  password = " + password);

    }
}
