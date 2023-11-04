package com.cf.sqlTest.api.designPatterns.responsibilityChainMode;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public class MiddleHandler extends Handler {
    public MiddleHandler(String name) {
        super(name);
    }

    @Override
    public void deal(Request req) {
        System.out.println(name + "处理" + req.toString());
        if (handler != null) {
            this.handler.deal(req);
        }
    }
}
