package com.cf.sqlTest.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author: lpy
 * @Date: 2023/08/25
 */
//@RestController
    @Controller
public class TestController {
    /**
     * transnal
     */
    @GetMapping("/a/{page}")
    public String page(@PathVariable("page") String page){
        return page;
    }

    @GetMapping("/add")
    public String add(){
        try {

        }catch (Exception e){
            return "redirect:error";
        }
        return "redirect:ok";
    }

    public static void main(String[] args) {
        // 多线程debug


    }
}
