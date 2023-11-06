package com.cf.sqlTest.api.designPatterns.interpreterMode;

/**
 * @author: lpy
 * @Date: 2023/11/06
 */
public class Client {
    public static void main(String[] args) {
        String input = "1 + 2 + 3";
        Context context = new Context(input);
        AddExpression addExpression = new AddExpression(
                new NumberExpression(1),
                new AddExpression(
                        new NumberExpression(2), new NumberExpression(3)
                )
        );
        int res = addExpression.interpret();
        System.out.println("解析结果："+res);
    }
}
