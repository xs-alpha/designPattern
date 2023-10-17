package com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode.detailCashStrategy2;

import com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode.Strategy;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class CashReturn2 implements Strategy {
    private double cashCondition;
    private double cashRet;

    public CashReturn2(double cashCondition, double cashRet){
        this.cashCondition = cashCondition;
        this.cashRet = cashRet;
    }

    @Override
    public double acceptCash(double price, int num) {
        if (cashCondition<0){
            throw new RuntimeException("error");
        }

        double result = price * num;
        if (result>cashCondition && (result - cashRet>0)){
            result = result - cashRet;
        }

        return result;
    }
}
