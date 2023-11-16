package com.cf.sqlTest.api.springLearn.v2.servlet;

import com.cf.sqlTest.api.springLearn.v2.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author: lpy
 * @Date: 2023/11/14
 */
public class GPDispatchServlet extends HttpServlet {
    // 享元模式  缓存
    private List<String> classNames = new ArrayList<>();

    private Properties contextConfig = new Properties();

    private Map<String,Object> ioc = new HashMap<>();

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
        method.invoke(ioc.get(beanName), paramValues);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1 加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        // ============================IoC部分===============================

        // 3.初始化ioc容器，将扫描到的类实例话，保存到ioc容器中
        doInstance();

        // AOP

        // ============================DI部分===============================

        // 4.完成依赖注入
        doAutowired();

        // ============================MVC部分===============================

        // 5.初始化HandlerMapping
        doInitHandlerMapping();

        System.out.println("XSDispatcherServlet 初始化完毕");

    }

    private void doInitHandlerMapping() {
        if (ioc.isEmpty()){return;}

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

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

    private void doAutowired() {
        if (ioc.isEmpty()){return;}

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 把所有的包括private,default,protected的都拿出来
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if (!field.isAnnotationPresent(GPAutoWired.class)){continue;}

                GPAutoWired annotation = field.getAnnotation(GPAutoWired.class);
                String beanName = annotation.value().trim();
                if ("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);

                try {
                    // ioc.get(beanName)相当于通过接口的全名拿到接口的实现类的实例
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> aClass = Class.forName(className);
                // 假设controller起名都非常规范
                if (aClass.isAnnotationPresent(GPController.class)){
                    String beanName = toLowerFirstCase(aClass.getSimpleName());
                    Object o = aClass.newInstance();
                    ioc.put(beanName,o);
                }else if(aClass.isAnnotationPresent(GPService.class)){
                    // 2.在多个包下出现相同的类名，只能自己起一个全局唯一的类名
                    String beanName = aClass.getAnnotation(GPService.class).value();
                    if ("".endsWith(beanName.trim())){
                        beanName = toLowerFirstCase(aClass.getSimpleName());
                    }

                    //1.默认的类名首字母小写
                    Object o = aClass.newInstance();
                    ioc.put(beanName,o);

                    // 3.如果是接口，判断有多少个实现类，如果是多个抛异常
                    for (Class<?> anInterface : aClass.getInterfaces()) {
                        if (ioc.containsKey(anInterface.getName())){
                            throw new RuntimeException("the "+anInterface.getName()+"is exits");
                        }
                        ioc.put(beanName,o);
                    }


                }
            }
        } catch (Exception e) {

        }
    }

    private String toLowerFirstCase(String name){
        char[] chars = name.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    private void doLoadConfig(String config) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(config);
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
                classNames.add(replace);
            }

        }
    }
}
