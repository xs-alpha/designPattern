
"只有一个User类和User操作类的时候，是只需要工厂方法模式的，但现
在显然你数据库中有很多的表，而SQL Server与Access又是两大不同的分
类，所以解决这种涉及多个产品系列的问题，有一个专门的工厂模式叫抽象
工厂模式。"

抽象工厂+简单工厂虽然抛弃了三大工厂类，实现了一定程度的解耦，但是
如果要再增加一个Oracle的话原本的抽象工厂只需要增加一个OracleFactory工厂类就可以啦，但是现在
就毕竟麻烦了，需要再DataAccess类中每个方法的switch都加上case了

    /**
     * ## Class类方法
     * public T newInstance()	创建对象
     * public String getName()	返回完整类名带包名
     * public String getSimpleName()	返回类名
     * public Field[] getFields()	返回类中public修饰的属性
     * public Field[] getDeclaredFields()	返回类中所有的属性
     * public Field getDeclaredField(String name)	根据属性名name获取指定的属性
     * public native int getModifiers()	获取属性的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Method[] getDeclaredMethods()	返回类中所有的实例方法
     * public Method getDeclaredMethod(String name, Class<?>… parameterTypes)	根据方法名name和方法形参获取指定方法
     * public Constructor<?>[] getDeclaredConstructors()	返回类中所有的构造方法
     * public Constructor getDeclaredConstructor(Class<?>… parameterTypes)	根据方法形参获取指定的构造方法
     * ----	----
     * public native Class<? super T> getSuperclass()	返回调用类的父类
     * public Class<?>[] getInterfaces()	返回调用类实现的接口集合
     * ————————————————
     *
     * ## Field类方法
     * public String getName()	返回属性名
     * public int getModifiers()	获取属性的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Class<?> getType()	以Class类型，返回属性类型【一般配合Class类的getSimpleName()方法使用】
     * public void set(Object obj, Object value)	设置属性值
     * public Object get(Object obj)	读取属性值
     *
     * public void setAccessible(boolean flag)	默认false，设置为true为打破封装
     * ————————————————
     *
     * ## Method类方法
     * public String getName()	返回方法名
     * public int getModifiers()	获取方法的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Class<?> getReturnType()	以Class类型，返回方法类型【一般配合Class类的getSimpleName()方法使用】
     * public Class<?>[] getParameterTypes()	返回方法的修饰符列表（一个方法的参数可能会有多个。）【结果集一般配合Class类的getSimpleName()方法使用】
     * public Object invoke(Object obj, Object… args)	调用方法
     * ————————————————
     *
     * ## Constructor类方法
     * public String getName()	返回构造方法名
     * public int getModifiers()	获取构造方法的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Class<?>[] getParameterTypes()	返回构造方法的修饰符列表（一个方法的参数可能会有多个。）【结果集一般配合Class类的getSimpleName()方法使用】
     * public T newInstance(Object … initargs)	创建对象【参数为创建对象的数据】
     * ————————————————
     *
     * 采摘自https://blog.csdn.net/qq_44715943/article/details/120587716
     */