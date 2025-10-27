package com.study.studyjava.proxy;

public class UserProxy implements Buy {
    /**
     *这个是真实对象，买房一定是真实对象来买的，中介只是跑腿的
     */
    private final Buy mBuy;
    public UserProxy(Buy buy) {
        this.mBuy = buy;
    }

    @Override
    public void buyHouse(long money) {
        /**
         * 这里是我们出钱去买房,中介只是帮忙
         */
        long newMoney = money + 200;
        mBuy.buyHouse(newMoney);
    }
}
