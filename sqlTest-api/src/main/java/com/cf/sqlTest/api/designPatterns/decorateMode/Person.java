package com.cf.sqlTest.api.designPatterns.decorateMode;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class Person implements ICharacter{
    private String name;

    public Person(String name){
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("装扮着"+name );
    }
}
