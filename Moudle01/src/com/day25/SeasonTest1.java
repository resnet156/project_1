package com.day25;

/**
 * @ClassName SeasonTest1
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 21:53
 * @Version 1.0
 **/
public class SeasonTest1 {
    public static void main(String[] args) {
        //toString()
        System.out.println(Season1.SPRING.toString());
        System.out.println("**********************");

        //values()
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

        System.out.println("**********************");

        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }

        //valueOf(String objName)
        Season1 spring = Season1.valueOf("SPRING");
        System.out.println(spring);
        spring.show();
    }
}
enum Season1 implements Info{
    SPRING("春天", "鸟语花香"){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天", "冰天雪地"){
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    private final String seasonName;
    private final String seasonDesc;

    private Season1(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

/*    @Override
    public void show() {
        System.out.println("这是一个季节！！！！");
    }*/

/*    @Override
    public String toString() {
        return "Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }*/
}

interface Info{
    void show();
}
