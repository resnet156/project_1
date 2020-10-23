package com.day25;

/**
 * @ClassName AnnotationTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/26 17:31
 * @Version 1.0
 **/
public class AnnotationTest {
}
class Person{
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void walk(){
        System.out.println("人走路");
    }
    public void eat(){
        System.out.println("人吃饭");
    }
}
interface Info1{
    void show();
}

