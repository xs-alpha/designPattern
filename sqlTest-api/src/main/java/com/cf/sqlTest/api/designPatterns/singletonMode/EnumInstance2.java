package com.cf.sqlTest.api.designPatterns.singletonMode;

/**
 * @author: lpy
 * @Date: 2023/10/31
 * @desc: 枚举类中可以声明方法
 */
public enum EnumInstance2 {
    INSTANCE{
        protected void testPrintFunc(){
            System.out.println("打印测试");
        }
    };

    private Object data;

    // 为了外部可以调用到testPrintFunc方法，否则不写下面的抽象方法，外部调用不到
//    protected abstract void testPrintFunc();

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumInstance2 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        EnumInstance2 instance = EnumInstance2.getInstance();
//        instance.testPrintFunc();
    }
}
