package com.day26;

/**
 * @ClassName HashSetTest
 * @Description TODO
 * 1,无序性
 * 2，不可重复性
 *添加元素的过程：
 * 我们向HashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值
 * 此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置(即为：索引位置)，判断数组此位置上是否有元素
 *      如果此位置没有元素——————>则添加成功-------->>>情况①
 *      如果此位置上有元素b（或有链表形式的多个元素）------>则比较a元素与b元素的哈希值：
 *          如果哈希值不相同------->则添加成功-------->>>情况②
 *          如果哈希值相同------>进而需要调用a元素的equal()方法：
 *              如果equal()返回true---->则a元素添加失败
 *              如果equal() 返回false---->则a元素添加成功-------->>>情况③
 *向Set中添加的数据，其所在类都必须重写hashCode()和equals()
 *重写的hashCode()和equals()尽可能的保持一致性，相等的对象具有相等的散列码
 *
 *
 *
 * @Author 李玉龙
 * @Date 2020/8/28 19:22
 * @Version 1.0
 **/
public class HashSetTest {
    public static void main(String[] args) {

    }
}
