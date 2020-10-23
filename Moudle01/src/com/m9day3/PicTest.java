package com.m9day3;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName PicTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/4 17:11
 * @Version 1.0
 **/
public class PicTest {
    //加密
    @Test
    public void test1(){
        BufferedInputStream bif = null;
        BufferedOutputStream bof = null;
        try {
            bif = new BufferedInputStream(new FileInputStream(new File("鹊羽鸦头.jpg")));
            bof = new BufferedOutputStream(new FileOutputStream(new File("鹊羽鸦头secret.jpg")));

            byte[] buffer = new byte[32];
            int len;
            while ((len = bif.read(buffer)) != -1){
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                bof.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bif != null){
                try {
                    bif.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bof != null){
                try {
                    bof.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    //解密
    @Test
    public void test3(){
        BufferedInputStream bif = null;
        BufferedOutputStream bof = null;
        try {
            bif = new BufferedInputStream(new FileInputStream(new File("鹊羽鸦头secret.jpg")));
            bof = new BufferedOutputStream(new FileOutputStream(new File("鹊羽鸦头3.jpg")));

            byte[] buffer = new byte[32];
            int len;
            while ((len = bif.read(buffer)) != -1){
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                bof.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bif != null){
                try {
                    bif.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bof != null){
                try {
                    bof.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
