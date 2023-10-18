package com.cf.sqlTest.api.designPatterns.factoryMethodMode;

import com.cf.sqlTest.api.designPatterns.factoryMethodMode.factories.AdvancedFactory;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.factories.BasicFactory;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.factories.IFacatory;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Operation;

/**
 * @author: lpy
 * @Date: 2023/10/17
 * @desc: 可以和原来的简单工厂模式的OperationFactory对比。
 */
public class OperationFactory {
    public static Operation createOperation(String optr){
        Operation opt = null;
        IFacatory ifc = null;
        switch (optr){
            case "+":
            case "-":
            case "*":
            case "/":
                ifc = new BasicFactory();
                break;
            case "pow":
            case "log":
                ifc = new AdvancedFactory();
                break;
        }
        opt = ifc.createOperation(optr);
        return opt;
    }
}
