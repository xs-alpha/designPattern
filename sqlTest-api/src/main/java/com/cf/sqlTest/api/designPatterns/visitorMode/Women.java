package com.cf.sqlTest.api.designPatterns.visitorMode;

/**
 * @author: lpy
 * @Date: 2023/11/07
 */
public class Women extends Person{
    @Override
    void accept(Action c) {
        c.getWoManConclusion(this);
    }
}
