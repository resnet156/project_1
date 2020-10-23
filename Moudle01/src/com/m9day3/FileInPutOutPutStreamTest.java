package com.m9day3;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName FileInPutOutPutStreamTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/4 8:41
 * @Version 1.0
 **/
public class FileInPutOutPutStreamTest {
    //测试FileInputStream和FileOutputStream
    /*
     结论:
     1，处理文本文件时(.txt, .java, .c, .cpp ......)，使用字符流
     2, 处理非文本文件时(.jpg, .mp3, .mp4, .avi, .doc, .ppt ......)，使用字节流



    */
    @Test
    public void FileInputStreamTest() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File("鹊羽鸦头.jpg");
            File desFile = new File("鹊羽鸦头1.jpg");

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(desFile);

            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void copyFile(String srcPath, String desPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcPath);
            File desFile = new File(desPath);

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(desFile);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
    @Test
    public void test2(){
        long start = System.currentTimeMillis();

        String srePath = "C:\\Users\\86182\\Desktop\\早上一柱擎天.ts";
        String desPath = "C:\\Users\\86182\\Desktop\\早上一柱擎天1.ts";

        copyFile(srePath, desPath);
        long end = System.currentTimeMillis();
        System.out.println("复制操作花费的时间为：" + (end - start));
    }


}
