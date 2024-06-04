package com.fwp.study.design.strategy;

public class AliPay implements PayStrategy {

    @Override
    public PayResult pay(PayParam payParam) {
        System.out.println("支付宝支付");
        return null;
    }
}
