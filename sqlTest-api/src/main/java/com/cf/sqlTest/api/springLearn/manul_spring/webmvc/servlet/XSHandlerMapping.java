package com.cf.sqlTest.api.springLearn.manul_spring.webmvc.servlet;

import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;

/**
 * @author: lpy
 * @Date: 2023/11/17
 */
@Data
@Accessors(chain = true)
public class XSHandlerMapping {
    // url
    private String url;

    // 对应的Method
    private Method method;

    // Method对应的实例对象
    private Object controller;
}
