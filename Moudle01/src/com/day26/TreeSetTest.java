package com.day26;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName TreeSetTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/28 20:41
 * @Version 1.0
 **/
public class TreeSetTest {
    public static void main(String[] args) {
        //1,向TreeSet中添加的数据，要求是相同类的对象

        TreeSet treeSet = new TreeSet();

/*        treeSet.add(123);
        treeSet.add(55);
        treeSet.add(16);
        treeSet.add(13);
        treeSet.add(23);

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        treeSet.add(new User("Tom", 23));
        treeSet.add(new User("Bi", 45));
        treeSet.add(new User("Jony", 2));
        treeSet.add(new User("MM", 63));
        treeSet.add(new User("Dave", 32));

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //3, 定制排序中，比较两个对象是否相同的标准为：Compare() 返回值为0，不再是equals()
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }else {
                    throw new RuntimeException("输入的数据异常");
                }
            }
        };

        TreeSet ts = new TreeSet(com);
    }
}
