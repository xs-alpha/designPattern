package com.cf.sqlTest.api.designPatterns.factoryMethodMode;

import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Log;
import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Pow;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class AdvancedFactory implements IFacatory{
    @Override
    public Operation createOperation(String optr) {
        Operation opt = null;
        switch (optr){
            case "pow":
                opt = new Pow();
                break;
            case "log":
                opt = new Log();
                break;
        }
        return opt;
    }
}
