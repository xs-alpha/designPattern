package com.cf.sqlTest.api.designPatterns.singletonMode;

/**
 * @author: lpy
 * @Date: 2023/10/31
 * @desc: 线程安全的
 */
public class StaticInnerClassSingleton {
    /**静态内部类，私有的: 静态内部类是基于类初始化的延迟加载解决方案![](https://image.devilwst.top/imgs/2023/10/0abc48a73ff32eee.png)*/
    /**InnerClass的初始化锁看哪个线程拿到，就先初始化他*/
    private static class InnerClass{
        private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }
    // 私有构造器必须要有
    private StaticInnerClassSingleton(){

    }
    // jvm在类的初始化阶段，也就是class被加载后，被线程使用之前，都是类的初始化阶段，在这个阶段会执行类的初始化，在执行类的初始化期间，jvm会去获取一个锁，这个锁可以同步多个线程对一个类的初始化,——Class对象的初始化锁
    // 基于这个特性，我们可以实现基于静态内部类的并且线程安全的延迟初始化方案，图中线程0的 2和3指令重排对于线程1并不会看到,非构造线程是不允许看到这个重排序的，/
    // java语言特性，出现下面五种情况，这个类将会被立刻初始化，
    // 1.有一个A类型的实例被创建，2.A类中声明的一个静态方法被调用，3.A类中声明的一个静态成员被赋值 ,4.A类中声明的一个静态成员被使用，并且这个成员不是一个常量成员，5.A类是一个顶级类,并且在这个类中有嵌套的断言语句
    public static StaticInnerClassSingleton getInstance(){
        return InnerClass.staticInnerClassSingleton;
    }
}
