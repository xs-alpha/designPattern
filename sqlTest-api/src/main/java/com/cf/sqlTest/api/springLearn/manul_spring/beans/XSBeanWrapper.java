package com.cf.sqlTest.api.springLearn.manul_spring.beans;

/**
 * @author: lpy
 * @Date: 2023/11/16
 */
public class XSBeanWrapper {
    private Object wrapper;
    private Class<?> wrapperClass;

    public XSBeanWrapper(Object instance) {
        this.wrapper = instance;
        this.wrapperClass = instance.getClass();
    }

    public Object getWrapperInstance() {
        return this.wrapper;
    }
    public Class<?> getWrapperedClass() {
        return this.wrapperClass;
    }
}
