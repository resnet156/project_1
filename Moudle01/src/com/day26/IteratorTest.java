package com.day26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @ClassName IteratorTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/26 20:06
 * @Version 1.0
 **/
public class IteratorTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new People("Jerry", 20));

        Iterator iterator = coll.iterator();

        //hashNest() 判读是否还有下一个元素
        while (iterator.hasNext()){
            //next()  ①指针下移  ②返回指针指向的元素
            System.out.println(iterator.next());
        }

        //remove()移除
        Iterator iterator1 = coll.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            if("Tom".equals(obj)){
                iterator1.remove();
            }
        }
        System.out.println("");
        Iterator itrtator2 = coll.iterator();
        while (itrtator2.hasNext()){
            System.out.println(itrtator2.next());
        }
    }
}
