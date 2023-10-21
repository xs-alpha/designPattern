package com.cf.sqlTest.api.designPatterns.observerMode.手写observer;

import com.cf.sqlTest.api.designPatterns.observerMode.手写observer.observer.NBAEmplObserver;
import com.cf.sqlTest.api.designPatterns.observerMode.手写observer.observer.Observer;
import com.cf.sqlTest.api.designPatterns.observerMode.手写observer.observer.SimpleEmplObserver;
import com.cf.sqlTest.api.designPatterns.observerMode.手写observer.subject.Secretary;

/**
 * @author: lpy
 * @Date: 2023/10/21
 */
public class ManualObserverTest {
    public static void main(String[] args) {
        Secretary xm = new Secretary("小秘");

        Observer lw = new NBAEmplObserver("老王", xm);
        Observer lwb = new SimpleEmplObserver("老王八", xm);

        xm.addEmpl(lw);
        xm.addEmpl(lwb);

        xm.setAction("小秘回来啦");
        xm.notifyEmpl();
    }
}
