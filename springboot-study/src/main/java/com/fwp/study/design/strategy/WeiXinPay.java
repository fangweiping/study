package com.fwp.study.design.strategy;

public class WeiXinPay implements PayStrategy {

    @Override
    public PayResult pay(PayParam payParam) {
        System.out.println("微信支付");
        return null;
    }
}
