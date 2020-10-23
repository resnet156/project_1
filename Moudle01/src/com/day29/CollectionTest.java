package com.day29;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName CollectionTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/29 8:43
 * @Version 1.0
 **/
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(789);
        coll.add(741);
        coll.add(852);

        coll.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Integer(11));
        list.add(new Integer(11));
        list.add(new Integer(22));
        list.add(new Integer(22));
        list.add(new Integer(33));

        list = DuplicateList(list);

        for(Object integer: list){
            System.out.println(integer);
        }

    }
    public static List DuplicateList(List list){
        HashSet hashSet = new HashSet();
        hashSet.addAll(list);
        return new ArrayList(hashSet);
    }
}
