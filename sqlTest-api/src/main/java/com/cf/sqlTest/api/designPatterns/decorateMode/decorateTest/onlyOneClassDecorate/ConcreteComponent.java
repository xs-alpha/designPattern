package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.onlyOneClassDecorate;

/**
 * @author: lpy
 * @Date: 2023/10/17
 * @desc: 如果只有一个ConcreteComponent类而没有抽象的Component类
 */
public class ConcreteComponent {
    private String name;
    public void operation(){
        System.out.print("装饰的"+name);
    }

    public ConcreteComponent(String name){
        this.name = name;
    }
    public ConcreteComponent(){
    }
}
