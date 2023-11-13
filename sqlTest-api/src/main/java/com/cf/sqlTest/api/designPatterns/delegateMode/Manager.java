package com.cf.sqlTest.api.designPatterns.delegateMode;

/**
 * @author: lpy
 * @Date: 2023/11/13
 */
public class Manager{
    private Worker w;

    public void setWorker(Worker worker){
        this.w = worker;
    }

    public void delegateTask(){
        if (null!= w){
            w.doTask();
        }else{
            System.out.println("没有找到合适的员工处理任务");
        }
    }


}

