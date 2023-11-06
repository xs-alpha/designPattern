package com.cf.sqlTest.api.designPatterns.interpreterMode;

/**
 * @author: lpy
 * @Date: 2023/11/06
 * @desc: 非终结符表达式
 */
public class AddExpression implements Interpreter{
    private Interpreter left;
    private Interpreter right;

    public AddExpression(Interpreter left, Interpreter right){
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret()+right.interpret();
    }
}
