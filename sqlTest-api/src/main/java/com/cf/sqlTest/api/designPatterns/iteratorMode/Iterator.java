package com.cf.sqlTest.api.designPatterns.iteratorMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public interface Iterator {
    Object first();
    Object next();
    boolean isDone();
    Object currentItem();
}
