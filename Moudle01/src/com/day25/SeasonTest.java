package com.day25;

/**
 * @ClassName SeasonTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 21:09
 * @Version 1.0
 **/
public class SeasonTest {
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
    }
}
class Season{
    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    public static final Season SPRING = new Season("春天", "鸟语花香");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "冰天雪地");

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

}