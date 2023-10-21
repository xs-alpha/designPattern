package com.cf.sqlTest.api.designPatterns.observerMode.jdk原生observer;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: lpy
 * @Date: 2023/10/21
 */
@Data
public class SimpleEmplObserver2 implements Observer {
    private String name;

    public SimpleEmplObserver2(String name){
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Subject2 s = (Subject2) o;
        System.out.println(s.getName()+","+s.getAction()+","+this.getName()+"赶紧工作");
    }
}
