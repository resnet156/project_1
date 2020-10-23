package com.m9day3;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName BufferedTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/4 11:16
 * @Version 1.0
 **/
public class BufferedTest {
    /*
    处理流之一：缓冲流的使用

    1.缓冲流
    BufferedInputStream
    BufferedOutputStream
    BufferedReader
    BufferedWriter
    作用：提高流的读取、写入速度
    提高读写速度的原因， 内部提供了一个缓冲区

    3，处理流，就是“套接”在已有的流的基础上

    */
    @Test
    public void BufferedStreamTest()  {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1,造文件
            File sreFile = new File("鹊羽鸦头.jpg");
            File desFile = new File("鹊羽鸦头2.jpg");
            //2,造流
            //2.1,, 造节点流
            FileInputStream fis = new FileInputStream(sreFile);
            FileOutputStream fos = new FileOutputStream(desFile);
            //2.2, 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //3,复制的细节，读取写入
            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //4, 流资源的关闭
        //要求先关闭外层的流，再关闭内层的流


        //关闭外层流的同时，内层流也会自动的进行关闭，关于内层流的关闭， 我们可以省略
    }

    public void copyFileWithBuffreed(String scrPath, String desPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1,造文件
            File sreFile = new File(scrPath);
            File desFile = new File(desPath);
            //2,造流
            //2.1,, 造节点流
            FileInputStream fis = new FileInputStream(sreFile);
            FileOutputStream fos = new FileOutputStream(desFile);
            //2.2, 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //3,复制的细节，读取写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testCopyFileWithBuffreed(){
        long start = System.currentTimeMillis();

        String srePath = "C:\\Users\\86182\\Desktop\\早上一柱擎天.ts";
        String desPath = "C:\\Users\\86182\\Desktop\\早上一柱擎天2.ts";

        copyFileWithBuffreed(srePath, desPath);
        long end = System.currentTimeMillis();
        System.out.println("复制操作花费的时间为：" + (end - start));
    }

    @Test
    public void testBufferedReaderAndBufferedWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //创建文件和相关的流
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));
            //
            //读写操作
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = br.read(cbuf)) != -1){
//                bw.write(cbuf, 0, len);
//            }
            String data;
            while ((data = br.readLine()) != null){
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //关闭流资源


    }

}
