package com.cf.sqlTest.api.designPatterns.simpleFactory;

/**
 * @author: lpy
 * @Date: 2023/09/13
 * @desc: 抽象操作类
 */
public abstract class Operate {
    /**
     * 抽象操作类的抽象操作方法
     * @param a
     * @param b
     * @return
     */
    public abstract double operate(double a, double b);
}
