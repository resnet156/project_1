package com.day25;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @ClassName CollectionTeat
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/26 18:11
 * @Version 1.0
 **/
public class CollectionTeat {
    public static void main(String[] args) {
        //add()
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add(new Date());

        //size()
        System.out.println(coll.size());

        //addAll()
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);

        System.out.println(coll.size());
        System.out.println(coll);

        //clear()
        coll.clear();

        //isEmpty()
        System.out.println(coll.isEmpty());

    }
}
