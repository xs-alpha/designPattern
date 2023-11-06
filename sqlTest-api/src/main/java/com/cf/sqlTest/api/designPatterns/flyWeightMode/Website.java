package com.cf.sqlTest.api.designPatterns.flyWeightMode;

/**
 * @author: lpy
 * @Date: 2023/11/06
 */
public abstract class Website {
    /**
     * 抽象方法
     * @param u
     */
    public abstract void use(User u);
}
