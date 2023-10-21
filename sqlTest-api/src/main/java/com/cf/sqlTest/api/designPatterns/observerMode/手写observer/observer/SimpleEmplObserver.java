package com.cf.sqlTest.api.designPatterns.observerMode.手写observer.observer;

import com.cf.sqlTest.api.designPatterns.observerMode.手写observer.subject.Subject;

/**
 * @author: lpy
 * @Date: 2023/10/21
 */
public class SimpleEmplObserver extends Observer{
    public SimpleEmplObserver(String name, Subject sub) {
        super(name, sub);
    }

    @Override
    public void update() {
        System.out.println("我是"+super.getSubject().getName()+","+super.getSubject().getAction()+","+super.getName()+"赶紧去工作");
    }
}
