package com.m9day3;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @ClassName RandomAccessFileTest
 * @Description TODO
 * RandomAccessFile的使用：
 * 1,RandomAccessFile类直接继承于，java.lang.Object类，实现了DateInput接口和DateOutput接口
 * 2,RandomAccessFile类，既可以作为输入流，也可以作为输出流
 *
 *
 * @Author 李玉龙
 * @Date 2020/9/6 11:13
 * @Version 1.0
 **/
public class RandomAccessFileTest {
    @Test
    public void test1(){
        RandomAccessFile raf = null;
        RandomAccessFile raf1 = null;
        try {
            raf = new RandomAccessFile(new File("鹊羽鸦头.jpg"), "r");
            raf1 = new RandomAccessFile(new File("鹊羽鸦头4.jpg"), "rw");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf.read(buffer)) != -1){
                raf1.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
