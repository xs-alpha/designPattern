package com.cf.sqlTest.api.designPatterns.observerMode.jdk原生observer.subject;

import lombok.Data;

import java.util.Observable;

/**
 * @author: lpy
 * @Date: 2023/10/21
 */
@Data
public class Subject2 extends Observable {
    private String name;

    private String action;
    public Subject2(String name){
        this.name = name;
    }

    public void setAction(String v){
        this.action = v;
        setChanged();
        notifyObservers();
    }
    @Override
    public void setChanged(){
        super.setChanged();
    }
}
