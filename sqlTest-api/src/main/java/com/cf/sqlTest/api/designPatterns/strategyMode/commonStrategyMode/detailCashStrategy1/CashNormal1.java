package com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode.detailCashStrategy1;

import com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode.Strategy;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 具体的算法策略
 */
public class CashNormal1 extends Strategy {
    @Override
    public double acceptCash(double price, int num) {
        return price * num;
    }
}
