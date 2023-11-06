package com.cf.sqlTest.api.designPatterns.interpreterMode;

/**
 * @author: lpy
 * @Date: 2023/11/06
 */
public class Context {
    private String input;
    public Context(String input){
        this.input = input;
    }
    public String getOutput(){
        return this.input;
    }
}
