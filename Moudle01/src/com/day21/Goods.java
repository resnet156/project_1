package com.day21;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/25 15:29
 * @Version 1.0
 **/
public class Goods implements Comparable{
    private String name;
    private int price;

    public Goods() {
    }

    public Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            Goods goods = (Goods) o;
            if(this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else {
                //return 0;
                return this.name.compareTo(goods.name);
            }
        }
        //方式二
        //  Double.compare(this.price, goods.price)
        throw new RuntimeException("传入的数据异常！！");
    }
}
