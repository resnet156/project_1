package com.day29;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName MapTest
 * @Description TODO
 *      |---->Map:(1.2)双列数据，存储(key--value)对的数据，类似于高中时的函数 y = f(x)
 *              |--->HashMap:(1.2)作为Map的主要实现类，线程不安全，效率高：可以存储null的key和value
 *                  |--->LinkedHashMap:(1.4)保证在遍历Map元素时，可以按照添加元素的顺序遍历
 *                              原因：在原有的HashMap底层结构的基础上，添加了一对指针，指向前一个和后一个
 *                              对于频繁的遍历操作，此类执行效率高于HashMap
 *              |---->TreeMap:(1.2)保证按照添加的key--value对进行排序，实现排序遍历，此时考虑key的自然排序或者遍历排序
 *                                  使用红黑树
 *              |---->Hashtable:(1.0)作为古老的实现类:线程安全，效率低，不能存储null的key和value
 *                  |--->Properties:(1.0)常用来处理配置文件，key和value都是String类型
 *
 *       HashMap的底层：数值+链表 (jdk7之前)
 *                     数值+链表+红黑树(jdk8)
 *
 *       Map结构的理解：
 *          Map中的key是无序的，不可重复的，使用Set存储所有的key ---->(以HashMap为例)key所在类需要重写equals()和hashCode()
 *          Map中的value是无序的，可重复的，使用Collection存储所有的value---->value所在类需要重写equals()
 *          一个键值对： key-value构成了一个Entry对象
 *          Map中的entry是无序的，不可重复的，使用Set存储所有的entry
 *
 *
 *
 * @Author 李玉龙
 * @Date 2020/8/29 15:21
 * @Version 1.0
 **/
public class MapTest {
    @Test
    public void test1(){
        Map map = new HashMap();
        //map = new Hashtable();
        map.put(null,11);
    }

    @Test
    public void test4(){
        Map map = new HashMap();

        map.put("AA", 123);
        map.put(45, 123);
        map.put("DD", "MM");

        //遍历所有的key集：keySet()
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //遍历所有的value集：values()
        Collection coll = map.values();
        Iterator iterator1 = coll.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

        //遍历所有的key-vslue
        //entrySet()
        Set entrySet = map.entrySet();
/*        for(Object o : entrySet){
            System.out.println(o);
        }*/
        Iterator iterator2 = entrySet.iterator();
        while (iterator2.hasNext()){
            Object o = iterator2.next();
            Map.Entry entry = (Map.Entry)o;
            System.out.println(entry.getKey() + "----->" + entry.getValue());
        }

    }


    @Test
    public void test3(){
        Map map = new HashMap();

        map.put("AA", 123);
        map.put(45, 123);
        map.put("DD", "MM");

        //get()
        System.out.println(map.get(45));

        //containsKey()
        System.out.println(map.containsKey("EE"));

        //containsValue()
        System.out.println(map.containsValue("MM"));
    }

    @Test
    public void test2() {
        Map map = new HashMap();

        //添加
        map.put("AA", 123);
        map.put(45, 123);
        map.put("DD", "MM");
        map.put("CC", 56);

        //修改
        map.put("CC", "PP");

        System.out.println(map);

        Map map1 = new HashMap();
        map.put("EE", 99);
        map.put(88, "LL");

        //putAll()
        map.putAll(map1);
        System.out.println(map);

        //remove()
        Object value = map.remove("CC");
        System.out.println(map);

        //clear()
        map.clear();
        System.out.println(map.size());
    }
}
