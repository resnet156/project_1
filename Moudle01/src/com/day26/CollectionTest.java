package com.day26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CollectionTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/26 19:11
 * @Version 1.0
 **/
public class CollectionTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new People("Jerry", 20));

        //contains()
        System.out.println(coll.contains(new People("Jerry", 20)));

        //containAll(Collection coll1)
        Collection coll1 = Arrays.asList(123, 456);
        System.out.println(coll.containsAll(coll1));

        //remove()
        //coll.remove(new People("Jerry", 20));
        System.out.println(coll);

        //removeAll()
        System.out.println("");
        System.out.println(coll);
        //coll.removeAll(coll1);
        System.out.println(coll);

        //retainAll()
        //coll.retainAll(coll1);
        System.out.println(coll);

        //equals()
        Collection coll2 = Arrays.asList(123, 456);
        System.out.println(coll1.equals(coll2));

        //hashCode()
        System.out.println(coll.hashCode());

        //toArray()
        Object[] arr = coll.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        //List接口
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC", "DD"});
        System.out.println(list);

    }


}
