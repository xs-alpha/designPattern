package com.cf.sqlTest.api.designPatterns.proxyMode.pursuitDemo;

/**
 * @author: lpy
 * @Date: 2023/10/17
 * @desc: 追求者
 */
public class Pursuit implements IGiveGift{
    // 美眉
    private Girl mm;

    public Pursuit(Girl mm){
        this.mm = mm;
    }

    @Override
    public void giveDolls() {
        System.out.println("亲爱的："+this.mm.getName()+"送你娃娃");
    }

    @Override
    public void giveFlowers() {
        System.out.println("亲爱的："+this.mm.getName()+"送你花");
    }

    @Override
    public void giveChocolate() {
        System.out.println("亲爱的："+this.mm.getName()+"送你巧克力");
    }
}
