package com.day29;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName EmployeeTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/29 9:24
 * @Version 1.0
 **/
public class EmployeeTest {
    //问题1使用自然排序
    @Test
    //按照名字排序
    public void test1(){
        TreeSet set = new TreeSet();

        Employee e1 = new Employee("liudehua", 54, new MyDate(1959, 6, 8));
        Employee e2 = new Employee("zhangyv", 14, new MyDate(2020, 4, 3));
        Employee e3 = new Employee("wuzetiawn", 34, new MyDate(2000, 7, 1));
        Employee e4 = new Employee("gougenglong", 20, new MyDate(2015, 8, 14));
        Employee e5 = new Employee("lianhengnbi", 35, new MyDate(1999, 3, 25));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    //按照生日日期的顺序排序
    @Test
    public void test2(){
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Employee && o2 instanceof Employee){
                    Employee e1 = (Employee)o1;
                    Employee e2 = (Employee)o2;

                    MyDate birthday1 = e1.getBirthday();
                    MyDate birthday2 = e2.getBirthday();

                    int minusYear = birthday1.getYear() - birthday2.getYear();
                    if(minusYear != 0){
                        return minusYear;
                    }

                    int minusMonth = birthday1.getMonth() - birthday2.getMonth();
                    if(minusMonth != 0){
                        return minusMonth;
                    }

                    int minusDay = birthday1.getDay() - birthday2.getDay();
                    if(minusDay != 0){
                        return minusDay;
                    }
                }else{
                    throw new RuntimeException("输入的数据类型不对！！！");
                }
                return 0;
            }
        });


        Employee e1 = new Employee("liudehua", 54, new MyDate(1959, 6, 8));
        Employee e2 = new Employee("zhangyv", 14, new MyDate(2020, 4, 3));
        Employee e3 = new Employee("wuzetiawn", 34, new MyDate(2000, 7, 1));
        Employee e4 = new Employee("gougenglong", 20, new MyDate(2015, 8, 14));
        Employee e5 = new Employee("lianhengnbi", 35, new MyDate(1999, 3, 25));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
