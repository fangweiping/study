package com.fwp.study.design.strategy;

public interface PayStrategy {

    /**
     * 支付
     *
     * @return
     */
    PayResult pay(PayParam payParam);

}
