package com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode;

import com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode.detailCashStrategy1.CashNormal1;
import com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode.detailCashStrategy1.CashRebate1;
import com.cf.sqlTest.api.designPatterns.strategyMode.commonStrategyMode.detailCashStrategy1.CashReturn1;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class StrategyModeTest1 {
    public static void main(String[] args) {
        CashContext cc = null;

        // 用户输入
        int cashType = 3;
        switch (cashType) {
            case 1:
                cc = new CashContext(new CashNormal1());
                break;
            case 2:
                cc = new CashContext(new CashRebate1(0.7d));
                break;
            case 3:
                cc = new CashContext(new CashReturn1(100d, 10d));
                break;
        }
        double result = cc.getResult(10.5, 10);
        System.out.println(result);
    }
}
