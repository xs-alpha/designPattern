package com.cf.sqlTest.api.designPatterns.decorateMode.煎饼果子例子;

/**
 * @author: lpy
 * @Date: 2023/11/10
 */
public class BattercakeWithEge extends BattercakeDecorate{
    public BattercakeWithEge(Battercake b) {
        super(b);
    }

    @Override
    public String getmsg() {
        return super.getmsg()+"加鸡蛋";
    }

    @Override
    public int getprice() {
        return super.getprice()+1;
    }
}
