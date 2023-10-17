package com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode;

/**
 * @author: lpy
 * @Date: 2023/09/13
 * @desc: 策略
 */
public abstract class Strategy {
    /**
     * 策略类， 所有支持的算法的公共接口
     * @param price
     * @param num
     * @return
     */
    public abstract double acceptCash(double price, int num);
}
