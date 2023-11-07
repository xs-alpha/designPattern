package com.cf.sqlTest.api.designPatterns.visitorMode;

/**
 * @author: lpy
 * @Date: 2023/11/07
 * @desc: Visitor
 */
public abstract class Action {
    abstract void getManConclusion(Man m);

    abstract void getWoManConclusion(Women m);
}
