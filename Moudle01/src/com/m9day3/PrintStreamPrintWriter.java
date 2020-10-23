package com.m9day3;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @ClassName PrintStreamPrintWriter
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/5 21:03
 * @Version 1.0
 **/
public class PrintStreamPrintWriter {
    @Test
    public void test1(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("D:\\IDEA_worldspace\\project_1\\Moudle01\\bb.txt");
            ps = new PrintStream(fos, true);
            if (ps != null){
                System.setOut(ps);
            }
            for (int i = 0; i <=225 ; i++) {
                System.out.print((char)i);
                if (i % 50 == 0){
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null){
                ps.close();
            }
        }

    }
}
