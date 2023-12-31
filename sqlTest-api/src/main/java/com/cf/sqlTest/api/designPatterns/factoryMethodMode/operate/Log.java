package com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class Log extends Operation {
    @Override
    public double getResult(double a, double b) {
        return Math.log(b)/Math.log(a);
    }
}
