package com.cf.sqlTest.api.designPatterns.proxyMode.pursuitDemo;

/**
 * @author: lpy
 * @Date: 2023/10/17
 * @desc：代理类
 */
public class Proxy implements IGiveGift{
    private Pursuit gg;

    public Proxy(Girl mm){
        this.gg = new Pursuit(mm);
    }
    @Override
    public void giveDolls() {
        this.gg.giveDolls();
    }

    @Override
    public void giveFlowers() {
        this.gg.giveFlowers();
    }

    @Override
    public void giveChocolate() {
        this.gg.giveChocolate();
    }
}
