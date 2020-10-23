package com.m9day10;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName MethodTest
 * @Description 获取运行时类的方法结构
 * @Author 李玉龙
 * @Date 2020/9/10 11:19
 * @Version 1.0
 **/
public class MethodTest {
    @Test
    public void test1(){
        //getMethods():获取当前运行时类及其所有父类中声明为public的方法
        Class<Person> clazz1 = Person.class;
        Method[] methods = clazz1.getMethods();
        for (Method m : methods){
            System.out.println(m);
        }
        System.out.println();

        //getDeclaredMethods():获取当前运行时类中的所有方法     (不包括父类中的方法)
        Method[] declaredMethods = clazz1.getDeclaredMethods();
        for (Method m : declaredMethods){
            System.out.println(m);
        }


    }
    @Test
    public void test2(){
        Class<Person> clazz2 = Person.class;
        Method[] declaredMethods = clazz2.getDeclaredMethods();
        for (Method m : declaredMethods){
            //1,获取方法声明的注解
            Annotation[] anno = m.getAnnotations();
            for (Annotation annotation : anno){
                System.out.println(annotation);
            }

            //2,权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            //3,返回值类型
            System.out.print(m.getReturnType().getName() + "\t");

            //4,方法名
            System.out.print(m.getName());
            System.out.print("(");
            //5,形参列表
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)){
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (i == parameterTypes.length - 1){
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + " args_" + i + ", ");
                }
            }
            System.out.print(")");
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length > 0){
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ", ");
                }
            }

            System.out.println();
        }
    }
}
