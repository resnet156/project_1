package com.m9day12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyTest
 * @Description TODO
 *
 * 动态代理的时例
 *
 *
 * @Author 李玉龙
 * @Date 2020/9/12 14:47
 * @Version 1.0
 **/

interface Human{

    String getBelief();

    void eat(String food);
}

//被代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃：" + food);
    }
}

//
class ProxyFactory{
    //调用此方法返回一个代理类的对象
    public static Object getProxyInstance(Object obj){//obj  被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);

    }
}

class HumanUtil{
    public void method1(){
        System.out.println("=====================通用方法一====================");
    }
    public void method2(){
        System.out.println("=====================通用方法二====================");
    }
}

class MyInvocationHandler implements InvocationHandler{
    //需要使用被代理类的对象进行赋值
    private Object obj;//

    public void bind(Object obj){
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法invoke
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HumanUtil util = new HumanUtil();
        util.method1();

        //method(): 即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        Object returnValue = method.invoke(obj, args);

        util.method2();
        return returnValue;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();

        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        String nation = proxyInstance.getBelief();
        System.out.println(nation);
        proxyInstance.eat("四川麻辣烫");

        System.out.println("*****************************");

        NikeClothFactory nike = new NikeClothFactory();
        ClothFactory proxyClothFatory = (ClothFactory) ProxyFactory.getProxyInstance(nike);
        proxyClothFatory.produceCloth();
    }
}
