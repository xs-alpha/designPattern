package com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode.detailCashStrategy2;

import com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode.Strategy;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 具体的算法策略
 */
public class CashNormal2 implements Strategy {
    @Override
    public double acceptCash(double price, int num) {
        return price * num;
    }
}
