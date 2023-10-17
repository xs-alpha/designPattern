package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.onlyOneClassDecorate;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class ConcreteDecorator3 extends Decorator{

    @Override
    public void operation(){
        System.out.print("具体装饰类——裤子+");
        super.operation();
    }
}
