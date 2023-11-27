package com.cf.sqlTest.api.springLearn.manul_spring.aop.support;

import com.cf.sqlTest.api.springLearn.manul_spring.aop.aspect.XSAdvice;
import com.cf.sqlTest.api.springLearn.manul_spring.aop.config.XSConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: lpy
 * @Date: 2023/11/20
 */
public class XSAdvisedSupport {
    private XSConfig config;
    private Class<?> targetClass;
    private Object instance;
    private Pattern pointCutClassPattern;
    private Matcher matcher;

    private Map<Method, Map<String, XSAdvice>> methodCache;

    public XSAdvisedSupport(XSConfig config) {
        this.config = config;
    }

    /**
     * 给ApplicationContext首先IoC中的对象初始化时调用，决定要不要生成代理类的逻辑
     * @return
     */
    public boolean pointCutMatch() {
        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    public void setTargetClass(Class<?> clazz) {
        this.targetClass = clazz;
    }

    public Class getTargetClass() {
        return this.targetClass;
    }

    public void setTarget(Object instance) {
        this.instance = instance;
    }

    /**
     * 根据一个目标代理类方法获得其对应的通知
     * @param method
     * @param o
     * @return
     * @throws Exception
     */
    public Map<String, XSAdvice> getAdvices(Method method, Object o) throws Exception {
        Map<String, XSAdvice> cache = methodCache.get(method);
        if (null == cache) {
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cache =
                    this.methodCache.put(m, cache);
        }
        return null;
    }

    // 解析配置文件的
    private void parse() {
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");

        // 三段
        // 第一段：方法的修饰符和返回值
        // 第二段：类名
        // 第三段：方法的名称和形参列表

        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1));

        methodCache = new HashMap<>();
        Pattern pointCutPattern = Pattern.compile(pointCut);
        try {
            Class<?> aspectClass = Class.forName(this.config.getAspectClass());
            HashMap<String, Method> aspectMethods = new HashMap<>();
            for (Method method : aspectClass.getMethods()) {
                aspectMethods.put(method.getName(), method);
            }
            // 已上都是初始化准备工作

            // 从这里开始封装XSAdvice
            for (Method method : this.targetClass.getMethods()) {
                String methodString = method.toString();
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }

                Matcher matcher = pointCutPattern.matcher(methodString);
                if (matcher.matches()) {
                    HashMap<String, XSAdvice> advices = new HashMap<>();
                    if (!(null == config.getAspectBefore() || "".equals(config.getAspectBefore()))) {
                        advices.put("before", new XSAdvice(aspectClass.newInstance(), aspectMethods.get(config.getAspectBefore())));
                    }
                    if (!(null == config.getAspectAfter() || "".equals(config.getAspectAfter())) ){
                        advices.put("after", new XSAdvice(aspectClass.newInstance(), aspectMethods.get(config.getAspectAfter())));
                    }
                    if (!(null == config.getAspectAfterThrow() || "".equals(config.getAspectAfterThrow())) ){
                        XSAdvice advice = new XSAdvice(aspectClass.newInstance(), aspectMethods.get(config.getAspectAfterThrow()));
                        advice.setThrowName(config.getAspectAfterThrowingName());
                        advices.put("afterThrow", advice);
                    }
                    methodCache.put(method, advices);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getTarget() {
        return null;
    }
}
