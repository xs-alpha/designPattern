package com.cf.sqlTest.api.designPatterns.iteratorMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class IteratorTest {
    public static void main(String[] args) {
        ConcreteAggregate ca = new ConcreteAggregate();
        ca.add("1");
        ca.add("2");
        ca.add("3");
        ca.add("4");
        ca.add("5");
        ca.add("6");
        ConcreteIterator iterator = new ConcreteIterator(ca);
        System.out.println(iterator.isDone());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.isDone());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.isDone());
    }
}
