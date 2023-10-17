package com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class CashContext {
    private Strategy strategy;

    public CashContext(Strategy strategy){
        this.strategy = strategy;
    }

    public double getResult(double price, int num){
        return this.strategy.acceptCash(price, num);
    }
}
