package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.ShoppingSaleSystemImprove;

/**
 * @author: lpy
 * @Date: 2023/10/12
 * @desc: 打折
 */
public class CashRebate2 extends CashSuper2{
    private double rebate;

    public CashRebate2(double rebate){
        this.rebate = rebate;
    }

    @Override
    public double acceptCash(double price, int num){
//        return super.acceptCash(price, num)*rebate;
        double res = price * num * rebate;
        return super.acceptCash(res,1);
    }
}
