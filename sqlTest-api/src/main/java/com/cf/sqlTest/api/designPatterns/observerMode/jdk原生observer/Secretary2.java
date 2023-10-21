package com.cf.sqlTest.api.designPatterns.observerMode.jdk原生observer;

import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/10/21
 */
@Data
public class Secretary2 extends Subject2 {

    public Secretary2(String name){
        super(name);
    }
}
