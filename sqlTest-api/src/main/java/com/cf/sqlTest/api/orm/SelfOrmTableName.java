package com.cf.sqlTest.api.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数校验
 *
 * @author: lpy
 * @Date: 2023/12/04
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfOrmTableName {

    String value() default "";
}