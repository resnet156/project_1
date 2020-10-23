package com.m9day12;

/**
 * @ClassName Girl
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/14 18:12
 * @Version 1.0
 **/
public class Girl {
    private String name;

    public Girl(String name) {
        this.name = name;
    }

    public Girl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
}
