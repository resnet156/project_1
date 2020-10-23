package com.m9day10;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectionTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/11 21:14
 * @Version 1.0
 **/
public class ReflectionTest {
    @Test
    public void test1() throws Exception {
        //获取运行时类的对象
        Class<Person> clazz1 = Person.class;
        Person p = clazz1.newInstance();

        //获取指定的属性
        Field id = clazz1.getField("id");
        //设置当前属性的值
        //set(): 参数1，指明设置哪个对象的属性， 参数2，将此属性设置为多少
        id.set(p, 1001);

        //获取当前属性的值
        //get(): 获取哪个对象的当前属性值
        int pId = (int) id.get(p);
        System.out.println(pId);
    }
    @Test
    public void test2() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<Person> clazz1 = Person.class;
        Person p = clazz1.newInstance();
        Field name = clazz1.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p, "Tom");
        System.out.println(name.get(p));


    }
    @Test
    public void testMethod() throws Exception {
        Class<Person> clazz1 = Person.class;
        Person p = clazz1.newInstance();
        Method show = clazz1.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        show.invoke(p, "CHINA");
        Method showDesc = clazz1.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        Object returnVal = showDesc.invoke(Person.class);
        System.out.println(returnVal);
    }

    @Test
    public void testCon() throws Exception {
        Class<Person> clazz1 = Person.class;
        Constructor<Person> declaredConstructor = clazz1.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Person tom = declaredConstructor.newInstance("Tom");
        System.out.println(tom);
    }
}
