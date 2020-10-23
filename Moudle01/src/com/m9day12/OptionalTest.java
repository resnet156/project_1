package com.m9day12;

import org.junit.Test;

import java.util.Optional;

/**
 * @ClassName OptionalTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/14 18:15
 * @Version 1.0
 **/
public class OptionalTest {
    @Test
    public void test1(){
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
    }
    @Test
    public void test2(){
        Girl girl = new Girl();
        //girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);

    }

    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }

    @Test
    public void test3(){
        Boy boy = new Boy();
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    public String getGirlName1(Boy boy){
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        Boy boy1 = optionalBoy.orElse(new Boy(new Girl("三上悠亚")));
        Girl boy1Girl = boy1.getGirl();
        Optional<Girl> optionalGirl = Optional.ofNullable(boy1Girl);
        Girl girl = optionalGirl.orElse(new Girl("桥本环奈"));
        return girl.getName();
    }
}
