package com.cf.sqlTest.api.designPatterns.simpleFactory;

import com.cf.sqlTest.api.designPatterns.simpleFactory.operate.Add;
import com.cf.sqlTest.api.designPatterns.simpleFactory.operate.Div;
import com.cf.sqlTest.api.designPatterns.simpleFactory.operate.Mul;
import com.cf.sqlTest.api.designPatterns.simpleFactory.operate.Sub;

/**
 * @author: lpy
 * @Date: 2023/09/13
 * @desc: 简单工厂类
 */
public class OperationFactory {
    /**
     * 注意这里是返回对象!!! 工厂是创建对象的，而不是为了结果
     * @param operator 操作符
     * @return 对象
     */
    public static Operate createOperation(String operator){
        Operate oper = null;
        switch (operator){
            case "+":
                oper = new Add();
                break;
            case "-":
                oper =  new Sub();
                break;
            case "x":
                oper = new Mul();
                break;
            case "/":
                oper =  new Div();
                break;
        }
        return oper;
    }
}
