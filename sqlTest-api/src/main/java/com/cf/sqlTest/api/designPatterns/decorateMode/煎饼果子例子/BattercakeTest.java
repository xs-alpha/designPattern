package com.cf.sqlTest.api.designPatterns.decorateMode.煎饼果子例子;

/**
 * @author: lpy
 * @Date: 2023/11/10
 */
public class BattercakeTest {
    public static void main(String[] args) {
        Battercake cake;
        cake = new BaseBattercake();
        cake = new BattercakeWithChang(cake);
        cake = new BattercakeWithEge(cake);

        System.out.println(cake.getmsg()+",价格："+cake.getprice());
    }
}
