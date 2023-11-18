package com.cf.sqlTest.api.springLearn.manul_spring.webmvc.servlet;

import java.io.File;

/**
 * @author: lpy
 * @Date: 2023/11/18
 */
public class XSViewResolver {
    private final String default_template_suffix_name = ".xstl";
    private File templateRootDir;
    public XSViewResolver(String template){
        String file = this.getClass().getClassLoader().getResource(template).getFile();
        templateRootDir = new File(file);
    }

    public XSView resolveViewName(String viewName){
        if (viewName==null||"".equals(viewName.trim())){
            return null;
        }
        viewName = viewName.endsWith(default_template_suffix_name) ? viewName : (viewName + default_template_suffix_name);
        File file = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
        return new XSView(file);
    }
}
