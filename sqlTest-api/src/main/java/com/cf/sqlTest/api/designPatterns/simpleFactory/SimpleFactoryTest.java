package com.cf.sqlTest.api.designPatterns.simpleFactory;

/**
 * @author: lpy
 * @Date: 2023/09/13
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        Operate operation = OperationFactory.createOperation("+");
        double operate = operation.operate(10d, 30d);
        System.out.println(operate);
    }
}
