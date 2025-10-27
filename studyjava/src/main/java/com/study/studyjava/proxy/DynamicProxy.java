package com.study.studyjava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    //被代理的类引用
    private Object mObject;

    public DynamicProxy(Object mObject) {
        this.mObject = mObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("DynamicProxy, args = " + args.length);
        for (Object object : args) {
            System.out.println("DynamicProxy, object = " + object);
        }
        //调用被代理类对象的方法
        Object result = method.invoke(mObject, args);
        return result;
    }
}
