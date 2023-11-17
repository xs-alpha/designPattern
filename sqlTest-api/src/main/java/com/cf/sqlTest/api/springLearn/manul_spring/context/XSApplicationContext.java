package com.cf.sqlTest.api.springLearn.manul_spring.context;

import com.cf.sqlTest.api.springLearn.annotation.GPAutoWired;
import com.cf.sqlTest.api.springLearn.annotation.GPController;
import com.cf.sqlTest.api.springLearn.annotation.GPService;
import com.cf.sqlTest.api.springLearn.manul_spring.beans.XSBeanWrapper;
import com.cf.sqlTest.api.springLearn.manul_spring.beans.config.XSBeanDefinition;
import com.cf.sqlTest.api.springLearn.manul_spring.beans.support.XSBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/11/16
 */
public class XSApplicationContext {
    private Map<String, XSBeanDefinition> beanDefinitionMap = new HashMap<>();
    private XSBeanDefinitionReader beanDefinitionReader;

    private Map<String, XSBeanWrapper> factoryBeanInstanceCache = new HashMap<>();
    private Map<String, Object> factoryBeanObjectCache = new HashMap<>();
    private Map<String, Object> ioc = new HashMap<>();

    public XSApplicationContext(String... configLocations){

        // 1.加载配置文件
        beanDefinitionReader = new XSBeanDefinitionReader();

        // 2.解析配置文件，封装成BeanDefinition
        List<XSBeanDefinition> beanDefinitions = beanDefinitionReader.loadBeanDefinitions();

        // 3.把BeanDefinition缓存起来
        try {
            doRegistBeanDefinition(beanDefinitions);
        } catch (Exception e) {
            e.printStackTrace();
        }

        doAutoWired();
    }

    private void doAutoWired() {
        // 调用getBean()
        // 这一步所有的bean并没有真正的实例化，还只是配置阶段
        for (Map.Entry<String, XSBeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            getBean(beanName);
        }
    }

    private void doRegistBeanDefinition(List<XSBeanDefinition> beanDefinitions) throws Exception {
        for (XSBeanDefinition beanDefinition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("the "+beanDefinition.getFactoryBeanName()+" exists");
            }
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
            beanDefinitionMap.put(beanDefinition.getBeanClassName(),beanDefinition);
        }
    }

    // bean的实例化，DI是从这个方法开始的
    public Object getBean(String beanName){
        // 1.先拿到BeanDefinition配置信息
        XSBeanDefinition definition = this.beanDefinitionMap.get(beanName);
        // 2.反射实例化newInstance()
        Object instance = instantiateBean(beanName,definition);
        // 3.封装成BeanWrapper
        XSBeanWrapper beanWrapper = new XSBeanWrapper(instance);
        // 4.保存到IoC容器
        factoryBeanInstanceCache.put(beanName,beanWrapper);
        // 5.执行依赖注入
        populateBean(beanName,definition,beanWrapper);
        return beanWrapper.getWrapperInstance();
    }

    private void populateBean(String beanName, XSBeanDefinition definition, XSBeanWrapper beanWrapper) {
        // 可能会涉及到循环依赖
        // 用两个缓存
        // 1.把第一次读取到结果为空的BeanDefinition存到第一个缓存
        // 2.等第一次循环之后，第二次循环再检查第一次的缓存，再进行赋值

        Object instance = beanWrapper.getWrapperInstance();
        Class<?> clazz = beanWrapper.getWrapperedClass();

        if (!clazz.isAnnotationPresent(GPController.class)||clazz.isAnnotationPresent(GPService.class)){
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 把所有的包括private,default,protected的都拿出来
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if (!field.isAnnotationPresent(GPAutoWired.class)){continue;}

                GPAutoWired annotation = field.getAnnotation(GPAutoWired.class);
                String autowiredBeanName = annotation.value().trim();
                if ("".equals(autowiredBeanName)){
                    autowiredBeanName = field.getType().getName();
                }
                field.setAccessible(true);

                try {
                    // ioc.get(beanName)相当于通过接口的全名拿到接口的实现类的实例
                    field.set(entry.getValue(),this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object instantiateBean(String beanName, XSBeanDefinition definition) {
        String className = definition.getBeanClassName();
        Object instance = null;
        try {
            if (this.factoryBeanObjectCache.containsKey(beanName)) {
                instance = this.factoryBeanObjectCache.get(beanName);
            } else {

                Class<?> clazz = Class.forName(className);

                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(beanName, instance);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return instance;
    }

    public Object getBean(Class beanClass){
        return getBean(beanClass.getName());
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }
}
