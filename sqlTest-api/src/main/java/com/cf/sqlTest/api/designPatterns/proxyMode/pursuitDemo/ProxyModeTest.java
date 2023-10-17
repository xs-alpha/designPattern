package com.cf.sqlTest.api.designPatterns.proxyMode.pursuitDemo;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class ProxyModeTest {
    public static void main(String[] args) {
        Girl a = new Girl("艾AA");

        Proxy proxy = new Proxy(a);
        proxy.giveChocolate();
        proxy.giveDolls();
        proxy.giveFlowers();
    }
}
