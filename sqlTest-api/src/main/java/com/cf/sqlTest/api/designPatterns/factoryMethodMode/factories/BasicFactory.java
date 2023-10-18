package com.cf.sqlTest.api.designPatterns.factoryMethodMode.factories;

import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Operation;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Add2;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Div2;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Mul2;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Sub2;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class BasicFactory implements IFacatory {
    @Override
    public Operation createOperation(String optr) {
        Operation op =null;
        switch (optr){
            case "+":
                op = new Add2();
                break;
            case "-":
                op = new Sub2();
                break;
            case "*":
                op = new Mul2();
                break;
            case "/":
                op = new Div2();
                break;
        }
        return op;
    }
}
