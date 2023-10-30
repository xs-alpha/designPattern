package com.cf.sqlTest.api.designPatterns.iteratorMode;

import java.util.ArrayList;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class ConcreteAggregate extends Aggregate{
    private ArrayList<Object> items = new ArrayList<>();
    @Override
    public Iterator createIterator() {
        return null;
    }

    public Object geCurrentItem(int index){
        return items.get(index);
    }
    public Object add(Object o){
        return items.add(o);
    }
    public int getCount(){
        return items.size();
    }
}
