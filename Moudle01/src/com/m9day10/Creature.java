package com.m9day10;

import java.io.Serializable;

/**
 * @ClassName Creature
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/10 9:17
 * @Version 1.0
 **/
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }

}
