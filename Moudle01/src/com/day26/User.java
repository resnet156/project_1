package com.day26;

import java.util.Objects;

/**
 * @ClassName User
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/28 20:49
 * @Version 1.0
 **/
public class User implements Comparable{
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    //1, 按名字从小到大排序

    //2, 两种排序方法， 自然排序(CompareTo) 和定制排序 (Comparator)

    //3, 自然排序中，比较两个对象是否相同的标准为：CompareTo() 返回值为0，不再是equals()
    //4, 定制排序中，比较两个对象是否相同的标准为：Compare() 返回值为0，不再是equals()
    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User u = (User)o;
            int i = this.getName().compareTo(u.getName());
            if(i != 0){
                return i;
            }else{
                return Integer.compare(this.getAge(), u.getAge());
            }
        }else{
            throw new RuntimeException("输入的类型不对");
        }
    }
}
