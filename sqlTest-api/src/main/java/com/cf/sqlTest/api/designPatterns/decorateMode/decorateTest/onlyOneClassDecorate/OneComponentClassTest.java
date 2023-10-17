package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.onlyOneClassDecorate;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public class OneComponentClassTest {
    public static void main(String[] args) {
        ConcreteComponent component = new ConcreteComponent("小米");
        ConcreteDecorator1 c1 = new ConcreteDecorator1();
        ConcreteDecorator2 c2 = new ConcreteDecorator2();
        ConcreteDecorator3 c3 = new ConcreteDecorator3();

        c1.setComponent(component);
        c2.setComponent(c1);
        c3.setComponent(c2);

        c3.operation();
    }
}
