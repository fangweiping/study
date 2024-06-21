package com.fwp.study.design.strategy;

public class MiniProgramPay implements PayStrategy{
    @Override
    public PayResult pay(PayParam payParam) {
        System.out.println("小程序支付");
        return null;
    }
}
