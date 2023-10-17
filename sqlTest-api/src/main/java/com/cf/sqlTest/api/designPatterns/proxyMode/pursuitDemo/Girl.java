package com.cf.sqlTest.api.designPatterns.proxyMode.pursuitDemo;

import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/10/17
 */
@Data
public class Girl {
    private  String name;

    public Girl(String name){
        this.name = name;
    }
}
