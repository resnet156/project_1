package com.m9day3;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName InputStreamReaderTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/4 18:54
 * @Version 1.0
 **/
public class InputStreamReaderTest {
    /*
    1, 处理流之二：转换流的使用
    转换流属于字符
    InputStreamReader:将一个字节输入流转换为字符输入流
    OutputStreamWriter:将一个字符输出流转换为字节输出流
    2, 作用：提供字节流于字符流之间的转换
    3,解码：字节、字节数组 ----> 字符、字符数组
      编码： 字符、字符数组 ----> 字节、字节数组

    4,编码集

    */
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("dbcp.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1){
            String s = new String(cbuf, 0, len);
            System.out.println(s);
        }
        isr.close();
    }

    @Test
    public void test2(){
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            File file1 = new File("dbcp.txt");
            File file2 = new File("dbcp_gdb.txt");

            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            isr = new InputStreamReader(fis, "utf-8");
            osw = new OutputStreamWriter(fos, "gbk");

            char[] cubf = new char[20];
            int len;
            while ((len = isr.read(cubf)) != -1){
                osw.write(cubf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
