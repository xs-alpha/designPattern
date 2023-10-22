package com.cf.sqlTest.api.designPatterns.observerMode.jdk原生observer.observer;

import com.cf.sqlTest.api.designPatterns.observerMode.jdk原生observer.subject.Subject2;
import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: lpy
 * @Date: 2023/10/21
 */
@Data
public class NBAEmplObserver2 implements Observer {
    private String name;
    public NBAEmplObserver2(String  name){
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        Subject2 s = (Subject2) o;
        System.out.println(s.getName()+","+s.getAction()+","+this.getName()+"赶紧工作");
    }
}
