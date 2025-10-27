package com.study.studyjava.proxy;

public class XiaoMing implements Buy {
    @Override
    public void buyHouse(long money) {
        System.out.println("我买房了，用了 " + money + " 钱 ");
    }
}
