package com.cf.sqlTest.api.designPatterns.observerMode.jdk原生observer;

/**
 * @author: lpy
 * @Date: 2023/10/21
 */
public class JDKObserverTest {
    public static void main(String[] args) {
        Secretary2 s2 = new Secretary2("小秘2号");

        NBAEmplObserver2 ne = new NBAEmplObserver2("孙悟空");
        SimpleEmplObserver2 se = new SimpleEmplObserver2("猪刚鬣");

        s2.addObserver(ne);
        s2.addObserver(se);

        s2.setAction("回来了");
//
//        s2.setChanged();
//        s2.notifyObservers();
    }
}
