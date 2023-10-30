package com.cf.sqlTest.api.designPatterns.compositeMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public abstract class Company {
    protected String name;
    public Company(String name){
        this.name = name;
    }
    public  void add(Company company){
        throw new UnsupportedOperationException("不支持添加操作");
    }
    public  void remove(Company company){
        throw new UnsupportedOperationException("不支持删除操作");
    }
    public  void display(int depth){
        throw new UnsupportedOperationException("不支持展示操作");
    }

    public  void lineDuty(){
        throw new UnsupportedOperationException("不支持执行操作");
    }

}
