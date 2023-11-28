package com.cf.sqlTest.api.springLearn.manul_spring.beans.support;

import com.cf.sqlTest.api.springLearn.manul_spring.beans.config.XSBeanDefinition;
import com.cf.sqlTest.api.springLearn.manul_spring.core.XSBeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/11/28
 */
public class XSDefaultListableBeanFactory implements XSBeanFactory {
    public static Map<String, XSBeanDefinition> beanDefinitionMap = new HashMap<>();
    @Override
    public Object getBean(Class beanClass) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    public static void doRegistBeanDefinition(List<XSBeanDefinition> beanDefinitions) throws Exception {
        for (XSBeanDefinition beanDefinition : beanDefinitions) {
            if (beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("the "+beanDefinition.getFactoryBeanName()+" exists");
            }
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
            beanDefinitionMap.put(beanDefinition.getBeanClassName(),beanDefinition);
        }
    }
}
