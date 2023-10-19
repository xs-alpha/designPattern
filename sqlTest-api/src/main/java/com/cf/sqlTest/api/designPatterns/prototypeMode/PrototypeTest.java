package com.cf.sqlTest.api.designPatterns.prototypeMode;

/**
 * @author: lpy
 * @Date: 2023/10/19
 */
public class PrototypeTest {
    public static void main(String[] args) {
        /**
         * String是一种拥有值类型特点的特殊引用类
         * 型，super.clone（）方法是这样，如果字段是值类型的，则对该字段执行
         * 逐位复制，如果字段是引用类型，则复制引用但不复制引用的对象；因此，
         * 原始对象及其副本引用同一对象。
         */
//        Resume xs = new Resume("小菜", 24);
//        xs.setPersonalInfo("洛杉矶","google","2000-9");
//
//        Resume xs1 = xs.clone();
//        xs1.setName("xiaoc ");
//        xs.display();
//        xs1.display();

        Resume xs = new Resume("小菜", 24);
        xs.setPersonalInfo("洛杉矶","google","2000-9");
        xs.setPe("接口平台","11");

        Resume xs1 = xs.clone();
        xs1.setName("xiaoc ");
        xs1.setPe("pm","13");
        /**
         * 由于浅复制的问题导致后面的数据覆盖了前面的数据
         * Resume{name='小菜', age=24, address='洛杉矶', company='google', birthDay='2000-9', projectTitle='pm', circle='13'}
         * Resume{name='xiaoc ', age=24, address='洛杉矶', company='google', birthDay='2000-9', projectTitle='pm', circle='13'}
         */
        xs.display();
        xs1.display();
    }
}
