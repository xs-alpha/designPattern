package com.cf.sqlTest.api.designPatterns.decorateMode;

import com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery.BlackShoes;
import com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery.NoTrousers;
import com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery.StrwHat;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 装饰器模式
 */
public class DecorateModeTest {
    public static void main(String[] args) {
        Person xc = new Person("橙心");

        System.out.println("第一种装扮");
        BlackShoes blackShoes = new BlackShoes();
        blackShoes.decorate(xc);

        StrwHat strwHat = new StrwHat();
        strwHat.decorate(blackShoes);

        NoTrousers noTrousers = new NoTrousers();
        noTrousers.decorate(strwHat);

        noTrousers.show();
    }
}
