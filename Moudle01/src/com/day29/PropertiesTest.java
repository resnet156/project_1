package com.day29;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropertiesTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 9:36
 * @Version 1.0
 **/
public class PropertiesTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            Properties properties = new Properties();

            fis = new FileInputStream("jdbc.properties");
            properties.load(fis);

            String name = properties.getProperty("name");
            String password = properties.getProperty("password");

            System.out.println("name:" + name + ", password:" + password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
