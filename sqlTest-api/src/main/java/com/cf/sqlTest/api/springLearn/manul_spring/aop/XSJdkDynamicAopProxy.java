package com.cf.sqlTest.api.springLearn.manul_spring.aop;

import com.cf.sqlTest.api.springLearn.manul_spring.aop.aspect.XSAdvice;
import com.cf.sqlTest.api.springLearn.manul_spring.aop.support.XSAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/11/20
 */
public class XSJdkDynamicAopProxy implements InvocationHandler {
    private XSAdvisedSupport config;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, XSAdvice> advices =  config.getAdvices(method,null);
        try {
            advices.get("before").invoke();

            method.invoke(null, args);

            advices.get("after").invoke();
        }catch (Exception e){
            advices.get("afterThrow").invoke();
        }

        return null;
    }

    public Object getProxy() {
        return null;
    }
}
