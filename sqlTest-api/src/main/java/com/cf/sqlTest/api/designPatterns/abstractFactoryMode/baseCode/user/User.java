package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/10/24
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
}
