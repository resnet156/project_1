package com.m9day10;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @ClassName FieldTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/10 10:44
 * @Version 1.0
 **/
public class FieldTest {
    @Test
    public void test(){
        //获取属性结构
        //getFields():获取当前运行时类及其父类的所有声明为public权限修饰符的属性
        Class<Person> clazz1 = Person.class;
        Field[] fields = clazz1.getFields();
        for(Field f : fields){
            System.out.println(f);
        }

        //getDeclaredFields():获取当前运行时类当中的所有属性    (不包含父类中声明的属性)
        Field[] declaredFields = clazz1.getDeclaredFields();
        for (Field f : declaredFields){
            System.out.println(f);
        }

    }
    @Test
    public void test2(){
        Class<Person> clazz1 = Person.class;
        Field[] declaredFields = clazz1.getDeclaredFields();
        for (Field f: declaredFields){
            //1,权限修饰符
            int modifiers = f.getModifiers();
            System.out.print(Modifier.toString(modifiers) + "\t");

            //2,数据类型
            Class<?> type = f.getType();
            System.out.print(type.getName() + "\t");

            //3,变量名
            String fName = f.getName();
            System.out.print(fName);
            System.out.println();
        }
    }
}
