package com.m9day8;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectionTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/8 16:38
 * @Version 1.0
 **/
public class ReflectionTest {
    @Test
    public void test1() throws Exception {
        Class<People> clazz = People.class;
        Constructor<People> cons = clazz.getConstructor(String.class, int.class);
        People tom = cons.newInstance("Tom", 22);
        System.out.println(tom);

        Field ages = clazz.getDeclaredField("age");
        ages.set(tom, 10);
        System.out.println(tom);
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(tom);
        System.out.println("*********************************");

        Constructor<People> cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        People bili = cons1.newInstance("Bili");
        System.out.println(bili);

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(bili, "HanMeimei");
        System.out.println(bili);

        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        showNation.invoke(bili, "中国");
        System.out.println(bili);
    }
    //获取Class的实例的方式
    @Test
    public void test2() throws ClassNotFoundException {
        //方式1:调用运行时类的属性: .class
        Class<People> clazz1 = People.class;
        System.out.println(clazz1);

        //方式2: 通过运行时类的对象
        People p1 = new People();
        Class<? extends People> clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式3： 调用Class的静态方法：forName(String classPath)
        Class<?> clazz3 = Class.forName("com.m9day8.People");
        System.out.println(clazz3);

        //方式4： 使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.m9day8.People");
        System.out.println(clazz4);


    }
}
