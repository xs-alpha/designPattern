package com.cf.sqlTest.api.springLearn.manul_spring.core;

/**
 * @author: lpy
 * @Date: 2023/11/28
 */
public interface XSBeanFactory {
    Object getBean(Class beanClass);

    Object getBean(String beanName);

//    void doRegistBeanDefinition(List<XSBeanDefinition> beanDefinitions) throws Exception;
}
