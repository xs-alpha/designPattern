package com.cf.sqlTest.api.designPatterns.simpleFactory.operate;

import com.cf.sqlTest.api.designPatterns.simpleFactory.Operate;

/**
 * @author: lpy
 * @Date: 2023/09/13
 */
public class Div extends Operate {
    @Override
    public double operate(double a, double b) {
        if (0==b){
            throw new RuntimeException("除数不能为0");
        }
        return a/b;
    }
}
