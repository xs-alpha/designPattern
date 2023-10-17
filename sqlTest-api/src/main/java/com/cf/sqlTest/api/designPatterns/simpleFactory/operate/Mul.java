package com.cf.sqlTest.api.designPatterns.simpleFactory.operate;

import com.cf.sqlTest.api.designPatterns.simpleFactory.Operate;

/**
 * @author: lpy
 * @Date: 2023/09/13
 */
public class Mul extends Operate {
    @Override
    public double operate(double a, double b) {
        return a*b;
    }
}
