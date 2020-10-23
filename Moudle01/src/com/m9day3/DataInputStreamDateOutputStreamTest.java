package com.m9day3;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName DataInputStreamDateOutputStreamTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/5 21:24
 * @Version 1.0
 **/
public class DataInputStreamDateOutputStreamTest {
    @Test
    public void test1(){
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("data.txt"));
            dos.writeUTF("Bili");
            dos.flush();
            dos.writeInt(18);
            dos.flush();
            dos.writeBoolean(true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void test2(){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("data.txt"));
            String name = dis.readUTF();
            int age = dis.readInt();
            boolean xing = dis.readBoolean();
            System.out.println("name = " + name);
            System.out.println("age = " + age);
            System.out.println("xing = " + xing);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
