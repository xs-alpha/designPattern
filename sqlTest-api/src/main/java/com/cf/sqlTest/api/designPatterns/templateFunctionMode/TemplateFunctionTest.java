package com.cf.sqlTest.api.designPatterns.templateFunctionMode;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class TemplateFunctionTest {
    public static void main(String[] args) {
        // 将子类变量的声明改成父类，利用了多态性，实现了代码的复用
        TestPaper testPaperA = new TestPaperA("学生A","大二");
        testPaperA.getInf();
        testPaperA.testQuestion1();
        testPaperA.testQuestion2();
        testPaperA.testQuestion3();
        testPaperA.testQuestion4();

        System.out.println("\n");
        TestPaper testPaperB = new TestPaperB("学生B","大三");
        testPaperB.getInf();
        testPaperB.testQuestion1();
        testPaperB.testQuestion2();
        testPaperB.testQuestion3();
        testPaperB.testQuestion4();
    }
}
