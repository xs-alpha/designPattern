package com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode;

import com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode.detailCashStrategy2.CashNormal2;
import com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode.detailCashStrategy2.CashRebate2;
import com.cf.sqlTest.api.designPatterns.strategyMode.strategyModeWithSimpleFactoryMode.detailCashStrategy2.CashReturn2;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 这里进行改造，将判断的部分由客户端转移，
 */
public class CashContext {
    private Strategy strategy;

    public CashContext(int cashType){
        switch (cashType) {
            case 1:
                this.strategy = new CashNormal2();
                break;
            case 2:
                this.strategy = new CashRebate2(0.7d);
                break;
            case 3:
                this.strategy = new CashReturn2(100d, 10d);
                break;
        }
    }

    public double getResult(double price, int num){
        return this.strategy.acceptCash(price, num);
    }
}
