package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/10/25
 */
@Data
@AllArgsConstructor
public class Department {
    private Integer id;

    private String name;

    private String fullPath;
}
