package com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode.detailCashStrategy1;

import com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode.Strategy;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class CashReturn1 extends Strategy {
    private double cashCondition;
    private double cashRet;

    public CashReturn1(double cashCondition, double cashRet){
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
