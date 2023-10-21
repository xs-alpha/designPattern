package com.cf.sqlTest.api.designPatterns.observerMode.手写observer.subject;

import com.cf.sqlTest.api.designPatterns.observerMode.手写observer.observer.Observer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lpy
 * @Date: 2023/10/21
 * @desc: boss
 */
@Data
public abstract class Subject {
    private String name;

    public Subject(String name){
        this.name = name;
    }

    /** 同事列表*/
    private List<Observer> empls =new ArrayList<>();
    /** 增加同事*/
    public void addEmpl(Observer o){
        empls.add(o);
    }
    /** 减少同事*/
    public void removeEmpl(Observer o){
        empls.remove(o);
    }

    /**默认通知*/
    public void notifyEmpl(){
        empls.forEach(o->{
            o.update();
        });
    }

    private String action;

}
