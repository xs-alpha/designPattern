package com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate;

import com.cf.sqlTest.api.designPatterns.factoryMethodMode.Operation;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class Add2 extends Operation {

    @Override
    public double getResult(double a,double b){
        return a+b;
    }
}
