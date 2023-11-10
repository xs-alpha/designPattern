package com.cf.sqlTest.api.designPatterns.decorateMode.煎饼果子例子;

/**
 * @author: lpy
 * @Date: 2023/11/10
 */
public class BattercakeDecorate extends Battercake{
    private Battercake b;
    public BattercakeDecorate(Battercake b){
        this.b = b;
    }
    @Override
    public String getmsg() {
        return this.b.getmsg();
    }

    @Override
    public int getprice() {
        return this.b.getprice();
    }
}
