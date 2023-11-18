package com.cf.sqlTest.api.springLearn.manul_spring.webmvc.servlet;

import com.cf.sqlTest.api.springLearn.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/11/17
 */
public class XSHandlerAdapter {
    public XSModelAndView handler(HttpServletRequest req, HttpServletResponse resp, XSHandlerMapping handler) throws Exception {
        // 这是缓存用来保存参数名称和参数索引
        HashMap<String, Integer> paramIndexMapping = new HashMap<>();
        Annotation[][] pa = handler.getMethod().getParameterAnnotations();
        for (int j = 0; j < pa.length; j++) {
            for (Annotation a : pa[j]) {
                if (a instanceof GPRequestParam){
                    String paramName = ((GPRequestParam) a).value();
                    if (!"".equals(paramName.trim())){

                        paramIndexMapping.put(paramName,j);
                    }
                }
            }
        }

        // 初始化一下
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (parameterType==HttpServletRequest.class|| parameterType==HttpServletResponse.class){
                paramIndexMapping.put(parameterType.getName(), i);
            }
        }

        // 去拼接实惨列表
        Map<String,String[]> params = req.getParameterMap();
        Object[] paramValues = new Object[parameterTypes.length];
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(params.get(param.getKey()))
                    .replaceAll("\\[|\\]", "")
                    .replaceAll("\\s+", ",");
            if (!paramIndexMapping.containsKey(param.getKey())){
                continue;
            }
            Integer index = paramIndexMapping.get(param.getKey());
            // 允许自定义的类型转换器
            paramValues[index] = castStringValue(value,parameterTypes[index]);

        }

        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
            Integer index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = req;
        }

        Object result = handler.getMethod().invoke(handler.getController(), paramValues);
        if (null == result||result instanceof Void){
            return null;
        }

        boolean isModelAndView = handler.getMethod().getReturnType() == XSModelAndView.class;
        if (isModelAndView){
            return (XSModelAndView) result;
        }

        return null;
    }

    private Object castStringValue(String value, Class<?> parameterType) {
        if (String.class==parameterType){
            return value;
        }else if (Integer.class==parameterType){
            return Integer.valueOf(value);
        }
//        ...这块不应该是硬编码，
        return null;
    }

}
