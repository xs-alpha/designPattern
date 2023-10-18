package com.cf.sqlTest.api.designPatterns.factoryMethodMode.factories;

import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Operation;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public interface IFacatory {
    Operation createOperation(String optr);
}
