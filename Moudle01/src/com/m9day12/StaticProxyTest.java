package com.m9day12;

/**
 * @ClassName StaticProxyTest
 * @Description TODO
 *
 * 静态代理：
 * 特点：代理类和被代理类在编译期间，就被确定下来
 *
 * @Author 李玉龙
 * @Date 2020/9/12 10:46
 * @Version 1.0
 **/

interface ClothFactory{
    void produceCloth();
}

//代理类
class proxyClothFactory implements ClothFactory{

    private ClothFactory factory;//用被代理类对象进行实例化

    public proxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");

        factory.produceCloth();

        System.out.println("代理工厂做一些收尾工作");

    }
}

//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("NIKE 工厂生产一批运动服");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类对象
        ClothFactory nike = new NikeClothFactory();
        //创建代理类对象
        ClothFactory proxyClothFactory = new proxyClothFactory(nike);

        proxyClothFactory.produceCloth();
    }
}
