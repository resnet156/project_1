package com.m9day8;

import org.junit.Test;

import java.util.Random;

/**
 * @ClassName NewInstanceTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/8 20:35
 * @Version 1.0
 **/
public class NewInstanceTest {
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<People> clazz1 = People.class;
        People people = clazz1.newInstance();
        System.out.println(people);
    }
    @Test
    public void test2(){
        for (int i = 0; i < 100; i++) {
            int num = new Random().nextInt(3);
            String classPath = "";

            switch (num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.m9day8.People";
                    break;
            }
            try {
                Object instance = getInstance1(classPath);
                System.out.println(instance);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }


    }

    public Object getInstance1(String classPath) throws Exception {
        Class<?> clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
