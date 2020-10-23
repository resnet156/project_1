package com.m9day3;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName FileReaderWriterTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/2 21:30
 * @Version 1.0
 **/
public class FileReaderWriterTest {
    @Test
    public void test1()  {
        FileReader fileReader = null;
        try {
            //1,实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");
            //2,提供具体的流
            fileReader = new FileReader(file);
            //3,数据的读入
            //read():返回读入的一个字符，如果达到末尾则返回-1
            int read = fileReader.read();
            while (read != -1){
                System.out.print((char)read);
                read = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4,流的关闭操作
            try {
                if(fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void test2() {
        FileReader fileReader = null;
        try {
            //1,File类的实例化
            File file = new File("hello.txt");
            //2,FileReader流的实例化
            fileReader = new FileReader(file);
            //3,读入的操作
            //read(char[] cbuf)返回每次读入cubf数组中字符的个数，如果达到文件末尾返回-1
            char[] cbuf = new char[5];
            int len;
            while ((len = fileReader.read(cbuf)) != -1){
                /*for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }*/
                String s = new String(cbuf, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4,关闭文件
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*从内存中写出数据到硬盘的文件里
    说明
    1,输出操作：对应的File可以不存在，不会报异常
    2,
            :File对应的硬盘里的文件如果不存在，在输出的过程中会自动创建此文件
             File对应的硬盘里的文件如果存在:
                    如果流使用的构造器是FileWriter(file, false)或FileWriter(file),则表示对文件的覆盖
                    如果流使用的构造器是FileWriter(file, true),则不会对文件进行覆盖操作




    */


    @Test
    public void testFileWriter(){
        FileWriter fileWriter = null;
        try {
            //1,提供File类的对象，指明写入到的文件
            File file = new File("hello1.txt");
            //2,提供FileWriter类的对象，用于数据的写出
            fileWriter = new FileWriter(file);
            //3,写出操作
            fileWriter.write("I have a dream!\n");
            fileWriter.write("You need to hava a dream");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileWriter != null){
                //4,流资源的关闭
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void testFileReaderFileWriter1() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1,创建File对象，指明读入和写出的文件
            File srcFile = new File("hello.txt");
            File desFile = new File("hello2.txt");
            //2,创建输入和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(desFile);
            //3,数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                fw.write(cbuf, 0, cbuf.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //4,流资源的关闭
    }

}
