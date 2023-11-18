package com.cf.sqlTest.api.springLearn.manul_spring.webmvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/11/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XSModelAndView {
    private String viewName;

    private Map<String,?> model;

    public XSModelAndView(Map<String, ?> model) {
        this.model = model;
    }

    public XSModelAndView(String name) {
        this.viewName = name;
    }
}
