package com.cf.sqlTest.api.designPatterns.iteratorMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class ConcreteIterator implements Iterator {
    private ConcreteAggregate aggregate;
    private int current = 0;

    public ConcreteIterator(ConcreteAggregate aggregate){
        this.aggregate = aggregate;
    }

    @Override
    public Object first() {
        return aggregate.geCurrentItem(0);
    }

    @Override
    public Object next() {
        Object ret = null;
        current++;
        if (current>aggregate.getCount()){
            return ret;
        }
        ret = aggregate.geCurrentItem(current);
        return ret;
    }

    @Override
    public boolean isDone() {
        return current >=aggregate.getCount();
    }

    @Override
    public Object currentItem() {
        return aggregate.geCurrentItem(current);
    }
}
