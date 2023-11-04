package com.cf.sqlTest.api.designPatterns.responsibilityChainMode;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public class TestResponsibility {
    public static void main(String[] args) {
        Handler preHandler = new PreHandler("预处理");
        Handler middleHandler = new MiddleHandler("中处理");
        Handler lastHandler = new LastHandler("后处理");
        preHandler.setNextHandler(middleHandler).setNextHandler(lastHandler);


        Request req = new Request().setLevel(5).setName("请求1").setNum(2);
        preHandler.deal(req);

        Request req2 = new Request().setLevel(6).setName("请求2").setNum(3);
        preHandler.deal(req2);
    }
}
