package com.day29;

import com.day26.User;
import org.junit.Test;

import java.util.*;

/**
 * @ClassName TreeMapTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/29 21:06
 * @Version 1.0
 **/
public class TreeMapTest {
    //向TreeMap中添加key-value,要求key必须是由同一个类创建的对象
    //因为要按key进行排序： 自然排序，定制排序

    //自然排序
    @Test
    public void test1(){
        TreeMap treeMap = new TreeMap();
        User u1 = new User("Tom", 26);
        User u2 = new User("Jerry", 34);
        User u3 = new User("Max", 46);
        User u4 = new User("Dire", 33);

        treeMap.put(u1, 99);
        treeMap.put(u2, 77);
        treeMap.put(u3, 54);
        treeMap.put(u4, 100);

        Set entrySet = treeMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            Map.Entry entry = (Map.Entry)o;
            System.out.println(entry.getKey() + "----->" + entry.getValue());
        }


    }

    //定制排序
    @Test
    public void test2(){
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;

                    return Integer.compare(u1.getAge(), u2.getAge());
                }else{
                    throw new RuntimeException("输入的数据类型不对！！！");
                }
            }
        });
        User u1 = new User("Tom", 26);
        User u2 = new User("Jerry", 34);
        User u3 = new User("Max", 46);
        User u4 = new User("Dire", 33);

        treeMap.put(u1, 99);
        treeMap.put(u2, 77);
        treeMap.put(u3, 54);
        treeMap.put(u4, 100);

        Set entrySet = treeMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            Map.Entry entry = (Map.Entry)o;
            System.out.println(entry.getKey() + "----->" + entry.getValue());
        }
    }
}
