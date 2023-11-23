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

    public boolean pointCutMatch() {
        return false;
    }

    public void setTargetClass(Class<?> clazz) {
        this.targetClass = clazz;
    }

    public void setTarget(Object instance) {
        this.instance = instance;
    }

    public Map<String, XSAdvice> getAdvices(Method method, Object o) throws Exception {
        Map<String, XSAdvice> cache = methodCache.get(method);
        if (null == cache) {
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cache =
                    this.methodCache.put(m, cache);
        }
        return null;
    }

    private void parse() {
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        String pointCutForClassRegex = pointCut.substring(0,pointCut.lastIndexOf("\\(")-4);
        pointCutClassPattern = Pattern.compile("class "+pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf("")+1));

        methodCache = new HashMap<>();
        Pattern pointCutPattern = Pattern.compile(pointCut);
    }
}
