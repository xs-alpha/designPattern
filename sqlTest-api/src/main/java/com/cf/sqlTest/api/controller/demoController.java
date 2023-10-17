package com.cf.sqlTest.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lpy
 * @Date: 2023/08/26
 */
@RestController
public class demoController {
    @GetMapping("/")
    public String error(){
        return "根路径";
    }
}
