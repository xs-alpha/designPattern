package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.ShoppingSaleSystemImprove;

/**
 * @author: lpy
 * @Date: 2023/10/12
 * @desc: 最基本的功能实现，单价X数量
 */
public class CashNormal2 implements ISale{

    @Override
    public double acceptCash(double price, int num) {
        return price*num;
    }
}
