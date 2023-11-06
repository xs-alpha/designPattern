package com.cf.sqlTest.api.designPatterns.interpreterMode;

/**
 * @author: lpy
 * @Date: 2023/11/06
 * @desc: 终结符表达式
 */
public class NumberExpression implements Interpreter{
    private int number;
    public NumberExpression(int number){
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}
