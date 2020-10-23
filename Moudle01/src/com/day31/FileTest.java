package com.day31;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName FileTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/31 16:08
 * @Version 1.0
 **/
public class FileTest {
    @Test
    public void test1(){
        //构造器1 ----->new File(String pathname)
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\IDEA_worldspace\\project_1\\Moudle01\\day31");

        System.out.println(file1);
        System.out.println(file2);

        //构造器2 ----->new File(String parent, String child)
        File file3 = new File("D:\\IDEA_worldspace","project_1");
        System.out.println(file3);

        //构造器3 -----> new File(File parent, String child)
        File file4 = new File(file3, "hi.txt");
        System.out.println(file4);

    }

    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));

        System.out.println();

        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
    }

    @Test
    public void test3(){
        File file1 = new File("D:\\IDEA_worldspace\\project_1");
        String[] list = file1.list();
        for (String s: list){
            System.out.println(s);
        }

        System.out.println();

        File[] files = file1.listFiles();
        for (File f:files){
            System.out.println(f);
        }
    }
    @Test
    public void test4(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");
        boolean renameTo = file2.renameTo(file1);
        System.out.println(renameTo);
    }
    @Test
    public void test5(){
        File file1 = new File("hello.txt");

        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println();

        File file2 = new File("D:\\io");
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

    }
    @Test
    public void test6() throws IOException {
        File file1 = new File("hi.txt");
        if(!file1.exists()){
            file1.createNewFile();
            System.out.println("创建成功");
        }else {
            file1.delete();
            System.out.println("删除成功");
        }
    }
    @Test
    public void test7(){
        File file1 = new File("D:\\io\\io1\\io3");
        boolean mkdir1 = file1.mkdir();
        if (mkdir1){
            System.out.println("创建成功1");
        }

        File file2 = new File("D:\\io\\io1\\io4");
        boolean mkdir2 = file2.mkdirs();
        if (mkdir2){
            System.out.println("创建成功2");
        }
    }
}
