package com.study.studyjava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Buy getProxy(Buy target) {
        return (Buy) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new BuyProxy(target));
    }

    private static class BuyProxy implements InvocationHandler {
        private Buy target;

        BuyProxy(Buy target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //扩展功能
            System.out.println("--------扩展功能------");
            return method.invoke(target, args);
        }
    }
}
