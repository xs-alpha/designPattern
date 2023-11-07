package com.cf.sqlTest.api.designPatterns.visitorMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lpy
 * @Date: 2023/11/07
 */
public class ObjectStructure {
    private final static List<Person> ps = new ArrayList<>();

    public void add(Person p){
        ps.add(p);
    }
    public void delete(Person p){
        ps.remove(p);
    }
    public void visitor(Action c){
        for (Person person : ps) {
            person.accept(c);
        }
    }
}
