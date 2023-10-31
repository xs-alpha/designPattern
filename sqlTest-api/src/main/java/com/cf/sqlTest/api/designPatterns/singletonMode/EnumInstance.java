package com.cf.sqlTest.api.designPatterns.singletonMode;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @author: lpy
 * @Date: 2023/10/31
 */
public enum  EnumInstance {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumInstance getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        EnumInstance instance = EnumInstance.getInstance();
        instance.setData(new Object());
        String filename = "singleton_file";

        // 即使是序列化，依然是同一个对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(instance);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filename)));
        EnumInstance enumInstance = (EnumInstance) ois.readObject();

        System.out.println(enumInstance.getData().hashCode() == instance.getData().hashCode());

        // 既然序列化没问题，那通过反射呢
        Class<EnumInstance> enumInstanceClass = EnumInstance.class;
        // 看java.lang.Enum类是一个抽象类，构造器没有无参构造器，public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {
//        Constructor<EnumInstance> declaredConstructor = enumInstanceClass.getDeclaredConstructor();
        Constructor<EnumInstance> declaredConstructor = enumInstanceClass.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        //在Constructor类的        if ((clazz.getModifiers() & Modifier.ENUM) != 0)
        //            throw new IllegalArgumentException("Cannot reflectively create enum objects");会找到无法通过反射创建的原因
        declaredConstructor.newInstance("xiaosheng",666);

        // io相关的类和反射相关的类来为枚举类保驾护航。
    }
}
