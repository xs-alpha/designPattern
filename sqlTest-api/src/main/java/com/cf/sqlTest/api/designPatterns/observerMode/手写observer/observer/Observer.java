package com.cf.sqlTest.api.designPatterns.observerMode.手写observer.observer;

import com.cf.sqlTest.api.designPatterns.observerMode.手写observer.subject.Subject;
import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/10/21
 * @desc: 抽象观察者
 */
@Data
public abstract class Observer {
    private String name;
    private Subject subject;

    public Observer(String name, Subject sub){
        this.name = name;
        this.subject = sub;
    }

    public abstract void update();
}
