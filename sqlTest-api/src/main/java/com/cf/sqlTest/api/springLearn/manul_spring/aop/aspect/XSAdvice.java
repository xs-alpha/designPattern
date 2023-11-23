package com.cf.sqlTest.api.springLearn.manul_spring.aop.aspect;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author: lpy
 * @Date: 2023/11/20
 */
@Data
public class XSAdvice {
    private Object aspect;
    private Method adviceMethod;
    private String throwName;

    public XSAdvice(Object aspect, Method adviceMethod){
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }
}
