package com.cf.sqlTest.api.springLearn.manul_spring.aop.config;

import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/11/20
 */
@Data
public class XSConfig {
    private String pointCut;
    private String aspectClass;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

}
