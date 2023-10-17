package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.ShoppingSaleSystemImprove;

import lombok.var;

/**
 * @author: lpy
 * @Date: 2023/10/12
 * @desc: 众多打折类的父类，本来
 */
public class CashSuper2 implements ISale{
    private ISale cashNormal;
    @Override
    public double acceptCash(double price, int num) {
        var result = 0d;
        if (null!=cashNormal){
            result = this.cashNormal.acceptCash(price,num);
        }
        return result;
    }

    public void setComponent(ISale cashNormal){
        this.cashNormal = cashNormal;
    }
}
