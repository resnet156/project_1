package com.m9day8;

/**
 * @ClassName People
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/8 16:34
 * @Version 1.0
 **/
public class People {
    private String name;
    public int age;

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private People(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void show(){
        System.out.println("你好，我是一个人");
    }
    private String showNation(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }
}
