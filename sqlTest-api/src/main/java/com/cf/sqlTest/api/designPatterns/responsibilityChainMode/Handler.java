package com.cf.sqlTest.api.designPatterns.responsibilityChainMode;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public abstract class Handler {
    protected String name;
    public Handler(String name){
        this.name = name;
    }
    // 这是核心，要组合自己，但this.handler和自己并不一定相等
    protected Handler handler;

    /**
     * nextHandler
     * @param handler
     */
    public Handler setNextHandler(Handler handler){
        this.handler = handler;
        return handler;
    }

    public abstract void deal(Request req);

}
