package com.fwp.study.design.strategy;

import lombok.Data;

@Data
public class PayContext {

    private PayStrategy payStrategy;

    public PayContext(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    public PayResult executePayStrategy(PayParam payParam) {
        return payStrategy.pay(payParam);
    }

    public static void main(String[] args) {
        AliPay aliPay = new AliPay();
        PayContext payContext = new PayContext(aliPay);
        PayParam payParam = new PayParam();
        payContext.executePayStrategy(payParam);
    }
}
