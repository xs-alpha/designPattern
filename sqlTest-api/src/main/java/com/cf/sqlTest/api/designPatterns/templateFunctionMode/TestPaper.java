package com.cf.sqlTest.api.designPatterns.templateFunctionMode;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public abstract class TestPaper {
    private String name;
    private String grade;

    public TestPaper(String name, String grade){
        this.name = name;
        this.grade = grade;
    }

    public void getInf(){
        System.out.println("学生："+this.name+"\n"+"年级"+this.grade);
    }

    public void testQuestion1(){
        System.out.println("第一道题："+answer1());
    }
    protected abstract String answer1();

    public void testQuestion2(){
        System.out.println("第二道题："+answer2());
    }
    protected abstract String answer2();

    public void testQuestion3(){
        System.out.println("第三道题："+answer3());
    }
    protected abstract String answer3();

    public void testQuestion4(){
        System.out.println("第四道题："+answer4());
    }
    protected abstract String answer4();
}
