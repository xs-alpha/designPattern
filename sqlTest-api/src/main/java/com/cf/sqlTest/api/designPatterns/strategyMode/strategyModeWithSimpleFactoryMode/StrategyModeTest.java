package com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class StrategyModeTest {
    public static void main(String[] args) {
        double result = new CashContext(1).getResult(10.9, 10);
        System.out.println(result);
    }
}
