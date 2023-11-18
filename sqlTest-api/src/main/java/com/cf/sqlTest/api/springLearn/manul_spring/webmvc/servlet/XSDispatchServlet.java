package com.cf.sqlTest.api.springLearn.manul_spring.webmvc.servlet;

import com.cf.sqlTest.api.springLearn.annotation.GPController;
import com.cf.sqlTest.api.springLearn.annotation.GPRequestMapping;
import com.cf.sqlTest.api.springLearn.manul_spring.context.XSApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: lpy
 * @Date: 2023/11/14
 */
public class XSDispatchServlet extends HttpServlet {
    private XSApplicationContext applicationContext;

    private List<XSHandlerMapping> handlerMapping = new ArrayList<>();
    private List<XSViewResolver> viewResolvers = new ArrayList<>();
    private Map<XSHandlerMapping,XSHandlerAdapter> handlerAdapters = new HashMap<>();

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
        // 完成了对handlerMapping的封装
        // 完成率对方法返回值 封装，封装成ModelAndView
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        // 1.通过url获得一个handlerMapping
        XSHandlerMapping handler = getHandler(req);
        if (null == handler){
            this.processDispatch(req,resp,new XSModelAndView("404"));
            return;
        }

        // 2.根据一个handlerMapping获得一个handlerAdapter
        XSHandlerAdapter ha = getHandlerAdapter(handler);

        // 3.解析一个方法的形参和返回值之后，同一封装为ModelAndView对象
        XSModelAndView mv = null;
        try {
            mv = ha.handler(req,resp,handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 把ModelAndView变成ViewResolver
        this.processDispatch(req,resp,mv);

    }

    private XSHandlerAdapter getHandlerAdapter(XSHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()){
            return null;
        }
        return this.handlerAdapters.get(handler);
    }

    private void processDispatch(HttpServletRequest req, HttpServletResponse resp, XSModelAndView mv) throws IOException {
        if (null==mv){
            return;
        }
        if (this.viewResolvers.isEmpty())return;

        for (XSViewResolver viewResolver : this.viewResolvers) {
            XSView xsView = viewResolver.resolveViewName(mv.getViewName());
            // 开始渲染
            xsView.render(mv.getModel(),req,resp);
        }


    }

    public XSHandlerMapping getHandler(HttpServletRequest req){
        if (this.handlerMapping.isEmpty())return null;
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        for (XSHandlerMapping mapping : handlerMapping) {
            Matcher matcher = mapping.getUrl().matcher(url);
            if (!matcher.matches()){
                continue;
            }
            return mapping;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        XSApplicationContext applicationContext = new XSApplicationContext(config.getInitParameter("contextConfigLocation"));

        // 9大组件初始化
        initStrategies(applicationContext);

        System.out.println("XSDispatcherServlet 初始化完毕");

    }


    private void initStrategies(XSApplicationContext context) {
        // 初始化多文件上传组件
//        initMultipartResolver(context);
        // 初始化语言组件
//        initLocaleResolver(context);
        // 初始化模板处理器
//        initThemeResolver(context);
        // handlerMappings
        initHandlerMappings(context);
        // 初始化参数适配器
        initHandlerAdapters(context);
        // 初始化异常处理器
//        initHandlerExceptionResolvers(context);
        // 初始化视图预处理器
//        initRequestToViewNameTranslator(context);
        // 初始化视图转换器
        initViewResolvers(context);
        // 初始化FlashMap管理器
//        initFlashMapManager(context);
    }

    private void initViewResolvers(XSApplicationContext context) {

    }

    private void initHandlerAdapters(XSApplicationContext context) {
        for (XSHandlerMapping handlerMapping : this.handlerMapping) {
            handlerAdapters.put(handlerMapping, new XSHandlerAdapter());
        }
    }

    private void initHandlerMappings(XSApplicationContext context) {
        if (applicationContext.getBeanDefinitionCount()==0){return;}

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object instance = applicationContext.getBean(beanName);
            Class<?> clazz = instance.getClass();

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
                String url = ("/"+baseUrl+"/"+ requestMapping.value().replaceAll("\\*",".*")).replaceAll("/+","/");
                Pattern pattern = Pattern.compile(url);
                handlerMapping.add(new XSHandlerMapping(pattern,method,instance));
                System.out.println("mapped :"+url+","+method.getName());
            }
        }
    }

}
