package com.cf.sqlTest.api.springLearn.manul_spring.beans.support;

import com.cf.sqlTest.api.springLearn.manul_spring.beans.config.XSBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: lpy
 * @Date: 2023/11/16
 */
public class XSBeanDefinitionReader {

    private Properties contextConfig = new Properties();
    // 享元模式  保存扫描到结果
    private List<String> registryBeanClasses = new ArrayList<>();

    public XSBeanDefinitionReader(String... configs) {
        // 这里为了简单，测试用，就只取了一个
        doLoadConfig(configs[0]);

        doScanner(contextConfig.getProperty("scanPackage"));
    }

    public List<XSBeanDefinition> loadBeanDefinitions() {
        ArrayList<XSBeanDefinition> res = new ArrayList<>();
        try {
            for (String registryBeanClass : registryBeanClasses) {
                Class<?> beanClass = Class.forName(registryBeanClass);
                // 保存类对应的className(全类名)
                // 还有BeanName
                // 1.默认首字母小写
                String beanName = toLowerFirstCase(beanClass.getSimpleName());
                String beanClassName = beanClass.getName();
                res.add(doCreateBeanDefinition(beanName, beanClassName));
                // 2.自定义

                // 3.接口注入
                for (Class<?> i : beanClass.getInterfaces()) {
                    res.add(doCreateBeanDefinition(i.getName(),beanClass.getName()));
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private XSBeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        XSBeanDefinition definition = new XSBeanDefinition();
        definition.setBeanClassName(beanClassName);
        definition.setFactoryBeanName(beanName);
        return definition;
    }

    private void doLoadConfig(String config) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(config.replaceAll("classpath:", ""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String replace = scanPackage + "." + file.getName().replace(".class", "");
                registryBeanClasses.add(replace);
            }

        }
    }

    private String toLowerFirstCase(String name){
        char[] chars = name.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return null;
    }
}
