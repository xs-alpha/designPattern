package com.cf.sqlTest.api.springLearn.manul_spring.webmvc.servlet;

import com.cf.sqlTest.api.springLearn.annotation.GPController;
import com.cf.sqlTest.api.springLearn.annotation.GPRequestMapping;
import com.cf.sqlTest.api.springLearn.annotation.GPRequestParam;
import com.cf.sqlTest.api.springLearn.manul_spring.context.XSApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/11/14
 */
public class XSDispatchServlet extends HttpServlet {
    private XSApplicationContext applicationContext;

    private Map<String, Method> handlerMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception !~~");
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        if (!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 not found!~~");
            return;
        }

        Map<String, String[]> params = req.getParameterMap();
        Method method = this.handlerMapping.get(url);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] paramValues = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (parameterType==HttpServletRequest.class){
                paramValues[i] = req;
            }else if (parameterType==HttpServletResponse.class){
                paramValues[i] = resp;
            }else if (parameterType==String.class){
                Annotation[][] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length; j++) {
                    for (Annotation a : pa[j]) {
                        if (a instanceof GPRequestParam){
                            String paramName = ((GPRequestParam) a).value();
                            if (!"".equals(paramName.trim())){
                                String s = Arrays.toString(params.get(paramName)).replaceAll("\\[|\\]", "")
                                        .replaceAll("\\s+", ",");
                                paramValues[i] = s;
                            }
                        }
                    }
                }
            }
        }


        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(applicationContext.getBean(beanName), paramValues);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        XSApplicationContext applicationContext = new XSApplicationContext(config.getInitParameter("contextConfigLocation"));

        // 5.初始化HandlerMapping
        doInitHandlerMapping();

        System.out.println("XSDispatcherServlet 初始化完毕");

    }

    private void doInitHandlerMapping() {
        if (applicationContext.getBeanDefinitionCount()==0){return;}

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Class<?> clazz = applicationContext.getBean(beanName).getClass();

            // 首先得是@GPController
            if (clazz.isAnnotationPresent(GPController.class)){continue;}

            String baseUrl = "";
            if (clazz.isAnnotationPresent(GPController.class)){
                GPController requestMapping = clazz.getAnnotation(GPController.class);
                baseUrl = requestMapping.value();

            }

            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(GPRequestMapping.class)){continue;}

                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                // 不管多少个/都替换成一个
                String url = ("/"+baseUrl+"/"+ requestMapping.value()).replaceAll("/+","/");
                handlerMapping.put(url,method);
                System.out.println("mapped :"+url+","+method.getName());
            }
        }
    }




    private String toLowerFirstCase(String name){
        char[] chars = name.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }




}
