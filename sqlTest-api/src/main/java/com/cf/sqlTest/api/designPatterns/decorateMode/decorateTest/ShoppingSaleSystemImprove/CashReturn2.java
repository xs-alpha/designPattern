package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.ShoppingSaleSystemImprove;

/**
 * @author: lpy
 * @Date: 2023/10/12
 */
public class CashReturn2 extends CashSuper2{
    private double retMoney;
    private double retCondition;

    public CashReturn2 (double retMoney, double retCondition){
        this.retMoney = retMoney;
        this.retCondition = retCondition;
    }


    @Override
    public double acceptCash(double price, int num){
        double result = price*num;

        if (result>retCondition && (result - retMoney>0)){
            result = result - retMoney;
        }
        return super.acceptCash(result,1);
    }
}
