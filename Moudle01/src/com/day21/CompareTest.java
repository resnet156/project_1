package com.day21;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName CompareTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 15:23
 * @Version 1.0
 **/
public class CompareTest {
    public static void main(String[] args) {
        String[] strings = {"BB", "NN", "OO", "AA", "GG", "CC"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));

        Goods[] goods = new Goods[4];
        goods[0] = new Goods("lianxiang", 56);
        goods[1] = new Goods("xiaomi", 34);
        goods[2] = new Goods("huawei", 98);
        goods[3] = new Goods("vivo", 17);

        Arrays.sort(goods);
        System.out.println(Arrays.toString(goods));

        //Comparator接口
        Arrays.sort(strings,new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String){
                    String s = (String) o1;
                    String s1 = (String) o2;
                    return -s.compareTo(s1);
                }
                //return 0;
                throw new RuntimeException("输入的数据类型不一致！！！");
            }
        });
        System.out.println(Arrays.toString(strings));

        Arrays.sort(goods, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof  Goods){
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;
                    if(g1.getName().equals(g2.getName())){
                        return  - Double.compare(g1.getPrice(), g2.getPrice());
                    }else return g1.getName().compareTo(g2.getName());
                }
                //return 0;
                throw new RuntimeException("输入的数据类型异常！！！");
            }
        });
    }
}
