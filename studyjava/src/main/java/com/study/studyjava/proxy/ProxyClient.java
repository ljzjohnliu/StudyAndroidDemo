package com.study.studyjava.proxy;

import java.lang.reflect.Proxy;

public class ProxyClient {
    public static void main(String[] args){
        System.out.println("静态代理测试");
        Buy buy = new XiaoMing();
        UserProxy proxy = new UserProxy(buy);
        proxy.buyHouse(1000000);

        System.out.println("动态代理测试");
        Buy dynamicProxy = (Buy) Proxy.newProxyInstance(buy.getClass().getClassLoader(),
                buy.getClass().getInterfaces(), new DynamicProxy(buy));
        dynamicProxy.buyHouse(1000000);
    }
}
