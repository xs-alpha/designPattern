package com.cf.sqlTest.api.designPatterns.decorateMode.煎饼果子例子;

/**
 * @author: lpy
 * @Date: 2023/11/10
 */
public class BaseBattercake extends Battercake{
    @Override
    public String getmsg() {
        return "煎饼";
    }

    @Override
    public int getprice() {
        return 5;
    }
}
