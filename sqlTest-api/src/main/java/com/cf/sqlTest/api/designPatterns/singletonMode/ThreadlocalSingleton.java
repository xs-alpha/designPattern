package com.cf.sqlTest.api.designPatterns.singletonMode;

/**
 * @author: lpy
 * @Date: 2023/10/31
 */
public class ThreadlocalSingleton {
    private static final ThreadLocal<ThreadlocalSingleton> ts = new ThreadLocal<ThreadlocalSingleton>(){
        @Override
        protected ThreadlocalSingleton initialValue() {
            return new ThreadlocalSingleton();
        }
    };

    public static ThreadlocalSingleton getInstance(){
        return ts.get();
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TS());
        Thread thread2 = new Thread(new TS());
        thread.start();
        thread2.start();

        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}

class TS implements Runnable{

    @Override
    public void run() {
        System.out.println(ThreadlocalSingleton.getInstance().hashCode());
    }
}