package com.cf.sqlTest.api.designPatterns.singletonMode;

/**
 * @author: lpy
 * @Date: 2023/10/31
 */
public class SingletonTest {
    public static void main(String[] args) throws Exception {
//        DCLSingleton instance = DCLSingleton.getInstance();
//        HungrySingleton instance1 = HungrySingleton.getInstance();
//        SimpleLazySingleton instance2 = SimpleLazySingleton.getInstance();

//        DCLSingleton dd = DCLSingleton.getInstance();
//        System.out.println(dd.hashCode()==instance.hashCode());

        Thread t1 = new Thread(new T());
        Thread t2 = new Thread(new T());
        t1.start();
        t2.start();

        System.out.println("end:");
//        HungrySingleton instance = HungrySingleton.getInstance();
//        String filename = "singleton_file";
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)));
//        oos.writeObject(instance);
//
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filename)));
//        HungrySingleton hungrySingleton = (HungrySingleton) ois.readObject();
//
//        System.out.println(hungrySingleton.hashCode()== instance1.hashCode()); //false
    }
}

class T implements Runnable {

    @Override
    public void run() {
        SimpleLazySingleton instance = SimpleLazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+" "+instance);
    }
}
