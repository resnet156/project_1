package com.m9day12;

/**
 * @ClassName Boy
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/14 18:12
 * @Version 1.0
 **/
public class Boy {
    private Girl girl;

    public Boy(Girl girl) {
        this.girl = girl;
    }

    public Boy() {
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}
