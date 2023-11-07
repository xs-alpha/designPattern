package com.cf.sqlTest.api.designPatterns.visitorMode;

/**
 * @author: lpy
 * @Date: 2023/11/07
 * @desc: Element
 */
public abstract class Person {
    abstract void accept(Action c);
}
