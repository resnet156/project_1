package com.m9day3;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName ObjectInputOutputStreamTest
 * @Description TODO
 * 1,序列化过程：将内存中的java对象保存到硬盘中，或者通过硬盘传输出去
 * 使用ObjectOutputStream实现
 *
 *
 *
 * @Author 李玉龙
 * @Date 2020/9/6 10:23
 * @Version 1.0
 **/
public class ObjectInputOutputStreamTest {
    //序列化
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            oos.writeObject(new String("我爱北京天安门"));
            oos.flush();
            oos.writeObject(new Person("连横臂", 28));
            oos.flush();
            oos.writeObject(new Person("过哦耿龙", 228, new Account(564.1235)));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //反序列化
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Object o = ois.readObject();

            String s = (String)o;
            System.out.println(s);
            Person p = (Person) ois.readObject();
            System.out.println(p);
            Person p1 = (Person) ois.readObject();
            System.out.println(p1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
