package com.cf.sqlTest.api.designPatterns.factoryMethodMode;

/**
 * @author: lpy
 * @Date: 2023/10/17
 * @desc: 可以和原来的简单工厂模式的OperationFactory对比。
 */
public class OperationFactory {
    public static  Operation createOperation(String optr){
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
