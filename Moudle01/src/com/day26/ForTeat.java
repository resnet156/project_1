package com.day26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ForTeat
 * @Description JDK 5.0 新增了foreach 循环， 用于遍历集合和数组
 * @Author 李玉龙
 * @Date 2020/8/26 20:33
 * @Version 1.0
 **/
public class ForTeat {
    public static void main(String[] args) {
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new People("Jerry", 20));

        for(Object obj: coll){
            System.out.println(obj);
        }

        int[] ints = {1, 2, 3, 4, 5};
        for(int i : ints){
            System.out.println(i);
        }

        String[] strings = {"MM", "MM", "MM"};

        for (int i = 0; i < strings.length; i++) {
            strings[i] = "GG";
        }
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

        Set set = new HashSet();
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("AA");
        set.add(new People("Tom", 16));
        set.add(new People("Tom", 16));

        System.out.println(set);
    }
}
