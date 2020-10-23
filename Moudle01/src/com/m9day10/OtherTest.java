package com.m9day10;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ClassName OtherTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/11 16:22
 * @Version 1.0
 **/
public class OtherTest {
    @Test
    public void test1(){
        Class<Person> clazz1 = Person.class;
        //1,getConstructors(): 获取当前运行时类中声明为public的构造器
        Constructor<?>[] constructors = clazz1.getConstructors();
        for (Constructor c : constructors){
            System.out.println(c);
        }
        System.out.println();
        //2,getDeclaredConstructors():获取当前运行时类中所有声明的构造器
        Constructor<?>[] declaredConstructors = clazz1.getDeclaredConstructors();
        for (Constructor c : declaredConstructors){
            System.out.println(c);
        }
    }

    //获取运行时类的父类
    @Test
    public void test2(){
        Class<Person> clazz1 = Person.class;
        Class<? super Person> superclass = clazz1.getSuperclass();
        System.out.println(superclass);
    }

    //获取运行时类的带泛型的父类
    @Test
    public void test3(){
        Class<Person> clazz1 = Person.class;
        Type genericSuperclass = clazz1.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    //获取运行时类的带泛型的父类的泛型
    @Test
    public void test4(){
        Class<Person> clazz1 = Person.class;
        Type genericSuperclass = clazz1.getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType)genericSuperclass;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
    }

    //获取运行时类的接口
    @Test
    public void test5(){
        Class<Person> clazz1 = Person.class;
        Class<?>[] interfaces = clazz1.getInterfaces();
        for (Class c : interfaces){
            System.out.println(c);
        }
        System.out.println();

        Class<? super Person> superclass = clazz1.getSuperclass();
        Class<?>[] interfaces1 = superclass.getInterfaces();
        for (Class c : interfaces1){
            System.out.println(c);
        }
    }
    //获取运行时类的包
    @Test
    public void test6(){
        Class<Person> clazz1 = Person.class;
        Package aPackage = clazz1.getPackage();
        System.out.println(aPackage);
    }
    //获取运行时类声明的注解
    @Test
    public void test7(){
        Class<Person> clazz1 = Person.class;
        Annotation[] annotations = clazz1.getAnnotations();
        for (Annotation a : annotations){
            System.out.println(a);
        }
    }
}
