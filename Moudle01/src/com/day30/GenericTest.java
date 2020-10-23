package com.day30;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName GenericTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 14:53
 * @Version 1.0
 **/
public class GenericTest {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>();

        list.add(123);
        list.add(45);
        list.add(63);
        list.add(72);
        list.add(43);

        for(Integer integer: list){
            int grade = integer;
            System.out.println(grade);
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }
    @Test
    public void test2(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("郭庚龙", 100);
        map.put("连横斌", 98);
        map.put("史泽龙", 110);

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "----->" + value);
        }
    }
}
