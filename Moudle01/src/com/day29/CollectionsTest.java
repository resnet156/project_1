package com.day29;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CollectionsTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 9:57
 * @Version 1.0
 **/
public class CollectionsTest {

    @Test
    public void test2(){
        List list = new ArrayList();

        list.add(123);
        list.add(15);
        list.add(45);
        list.add(36);
        list.add(75);

        List list1 = Arrays.asList(new Object[list.size()]);
        Collections.copy(list1, list);
        System.out.println(list1);
    }

    @Test
    public void test1(){
        List list = new ArrayList();

        list.add(123);
        list.add(15);
        list.add(45);
        list.add(36);
        list.add(75);
        System.out.println(list);

        //反转reverse()
        //Collections.reverse(list);

        //随机排列shuffle()
        //Collections.shuffle(list);

        //自然排序sort()
        //Collections.sort(list);

        //定制排序sort()
/*        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer){
                    Integer i1 = (Integer)o1;
                    Integer i2  = (Integer)o2;
                    return -Integer.compare(i1, i2);
                }else{
                    throw new RuntimeException("输入的数据异常！！！");
                }
            }
        });*/

        //swap()
        //Collections.swap(list, 1, 2);

        //frequency()
        //int frequency = Collections.frequency(list, 45);
        //System.out.println(frequency);


        System.out.println(list);


    }
}
