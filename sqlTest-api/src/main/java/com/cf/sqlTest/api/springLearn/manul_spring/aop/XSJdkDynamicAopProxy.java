package com.cf.sqlTest.api.springLearn.manul_spring.aop;

import com.cf.sqlTest.api.springLearn.manul_spring.aop.aspect.XSAdvice;
import com.cf.sqlTest.api.springLearn.manul_spring.aop.support.XSAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        Object returnValue;
        try {
            invokeAdvice(advices.get("before"));

            returnValue = method.invoke(this.config.getTargetClass(), args);

            invokeAdvice(advices.get("after"));
        }catch (Exception e){
            advices.get("afterThrow").invoke();
            throw e;
        }

        return returnValue;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),this.config.getTargetClass().getInterfaces(), this);
    }
    public void invokeAdvice(XSAdvice advice){
        try {
            advice.getAdviceMethod().invoke(advice.getAspect());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
