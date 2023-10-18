package com.cf.sqlTest.api.designPatterns.factoryMethodMode;

import com.cf.sqlTest.api.designPatterns.factoryMethodMode.operate.Operation;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        Operation operation = OperationFactory.createOperation("+");
        System.out.println(operation.getResult(10, 20));
        System.out.println(OperationFactory.createOperation("pow").getResult(10, 30));
    }
}
