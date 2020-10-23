package com.day30;

import java.util.List;

/**
 * @ClassName DAOTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 21:17
 * @Version 1.0
 **/
public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();

        dao.save("1001", new User(1001, 25,"连横斌"));
        dao.save("1002", new User(1002, 74,"郭庚龙"));
        dao.save("1003", new User(1003, 36,"王浩"));
        dao.save("1004", new User(1004, 18,"史泽龙"));

        List<User> list = dao.list();
        list.forEach(System.out::println);
    }
}
