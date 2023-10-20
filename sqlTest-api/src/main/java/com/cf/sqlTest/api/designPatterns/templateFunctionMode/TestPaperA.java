package com.cf.sqlTest.api.designPatterns.templateFunctionMode;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class TestPaperA extends TestPaper{

    public TestPaperA(String name, String grade) {
        super(name, grade);
    }

    @Override
    public String answer1() {
        return "A";
    }

    @Override
    public String answer2() {
        return "B";
    }

    @Override
    public String answer3() {
        return "C";
    }

    @Override
    public String answer4() {
        return "D";
    }
}
