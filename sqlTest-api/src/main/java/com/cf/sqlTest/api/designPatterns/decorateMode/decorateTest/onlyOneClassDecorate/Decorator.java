package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.onlyOneClassDecorate;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
public abstract class Decorator extends ConcreteComponent{
    private ConcreteComponent concreteComponent;

    public Decorator(String name) {
        super(name);
    }
    public Decorator() {
    }

    public void setComponent(ConcreteComponent c){
        this.concreteComponent = c;
    }

    @Override
    public void operation(){
        if (concreteComponent!=null){
            concreteComponent.operation();
        }
    }
}
