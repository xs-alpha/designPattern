# spring简化开发的四个基本策略

- 基于pojo的轻量级和最小侵入性编程

- 通过依赖注入和面向接口松耦合

- 基于切面和惯性进行声明式编程

- 通过切面和模板减少样板式代码

# spring编程思想

![](https://image.devilwst.top/imgs/2023/11/2146ea2c8608568d.png)

![](https://image.devilwst.top/imgs/2023/11/a12c09fe5344a131.png)
![](https://image.devilwst.top/imgs/2023/11/e8f7c6e4403769b3.png)
![](https://image.devilwst.top/imgs/2023/11/4cd00567ab2da32e.png)

![](https://image.devilwst.top/imgs/2023/11/27bb8a4cf5155900.png)
![](https://image.devilwst.top/imgs/2023/11/eb2351374c3ade19.png)
![](https://image.devilwst.top/imgs/2023/11/7462e61a6a39af1c.png)

# spring注解
![](https://image.devilwst.top/imgs/2023/11/d8c309f8f60bc33e.png)
@Configuration @Bean默认是类名首字母小写，其次是取方法名， 最后优先取Bean注解的value
@Scope prototype:原型 单例， singleton：单例  ，request:主要应用于web模块，同一次请求只创建一个实例， session：主要应用于web模块，同一个session只创建一个对象
@Lazy 默认是非延迟加载的，加了@Lazy注解就延迟加载,默认容器启动时不创建对象，调用对象的功能的时候才创建，但这里有一个坑，延时加载懒加载只针对单例Bean起作用,对于多例Bean反正都是用到的时候才记载
@Conditional Spring4开始提供，他的作用是按照一定的条件进行判断，满足条件给容器注册Bean
@Import 导入外部资源,可以手动指定一个第三方资源加到ioc容器中


给IoC容器注册Bean的方式
1.@Bean直接导入单个类
2.@ComponentScan默认扫描（@Controller,@Service,@Repository,@Component）
3.@Import 快速给容器导入Bean的一种方式 a.@Import直接参数导入 b.实现ImportSelector接口，自定义规则实现 c.实现ImportBeanDefinitionRegistrar 获得BeanDefinitionRegistry可以手动直接往IoC容器中注入值。
4.FactoryBean 把需要注册的对象封装为FactoryBean a.负责将Bean注册到IoC的Bean， b.BeanFactory从容器中获得Bean对象

对Bean声明周期的监控
1.配置@Bean的参数
2.分别实现InitializingBean和DisposableBean接口
3.使用@PostConstruct和@PreDestroy注解
4.自己写一个类，实现BeanPostProcessor接口

![](https://image.devilwst.top/imgs/2023/11/3ebd5b4caceef65e.png)

@Value()支持的类型
1.基本数据类型
2.支持spring EL表达式@Value("#{}")   
3.@Value("${}")

@Autowired:默认按类型装配，如果想使用按名称装配，可以结合@Qualifier注解一起使用
@Resource:默认按名称装配，当找不到与名称匹配的bean才会按类型装配


![](https://image.devilwst.top/imgs/2023/11/4a1b22ede8f61525.png)


# spring
![](https://image.devilwst.top/imgs/2023/11/0d58a2fd7192c2ae.png)

ApplicationContext简单的理解为他就是一个工厂类
getBean()从IOC容器中去获取一个实例的方法

在调用servlet init()方法时就要初始化ApplicationContext

spring终发生DI由getBean()触发
1.调用getBean（）创建对象
2.立即就会发生DI

1.调用servlet init()方法
    创建ApplicationContext
    BeanDefinitionReader读取配置文件和解析配置文件
2.读取配置文件（properties, xml,yml）
3.扫描相关的类  配置文件到了内存里统一都是BeanDefinition  (所有的配置文件在内存里的体现形式就是BeanDefinition)
4.初始化IoC容器，并且实例化对象   BeanWrapper
    ApplicationContext.getBean()去完成对象的实例化和依赖注入，对象最终会变成BeanWrapper包装起来
5.完成DI注入

![](https://image.devilwst.top/imgs/2023/11/22b91f7f14561d23.png)
![](https://image.devilwst.top/imgs/2023/11/1df3c248c1513690.png)


## spring AOP
![](https://image.devilwst.top/imgs/2023/11/2953137d08707191.png)

##　回顾
![](https://image.devilwst.top/imgs/2023/11/667c8b0d37eeded8.png)

![](https://image.devilwst.top/imgs/2023/11/0f3db48e760c21e3.png)



# DI
DI从getBean方法开始，因为spring默认是懒加载

实例化有两种情况：1.目标类配置了AOP,实例化对象代理类 2.目标类没有配置AOP,实例化原生对象

BeanWrapper:统一一个对外访问对象的入口
扩展一些功能，缓存一些配置信息

![](https://image.devilwst.top/imgs/2023/11/bf1a403ec492c01f.png)

instantiateBean() 由这个方法发起实例化对象的动作

populateBean() 由这个方法发起依赖注入动作

getBean()->doGetBean()->createBean()->doCreateBean()->
createBeanInstance(): 用反射创建了个对象示例 封装成BeanWrapper

populateBean() : 根据beanName beanDefinition BeanWrapper找到需要赋值的属性 
                把需要赋值的属性封装成了一个集合PropertyValues , 集合的元素PropertyValue
                
applyPropertyValues(): 循环propertyValue() 挨个调用BeanWrapper的setValue()方法，用反射调用setter方法完成赋值                


## 缓存

beanDefinitionMap: 用来存BeanDefinitionMap, 用来存储配置信息

factoryBeanObjectCache: 用来存原生Bean的Map, 存储反射创建出的实际的对象

factoryBeanInstanceCache: 用来存BeanWrapper的Map, 存储原生Bean的包装类


# AOP
![](https://image.devilwst.top/imgs/2023/11/358726a888879319.png)

AOP其实就两个最核心的累分别是MethodInterceptor, MethodInvocation
其他的都是边边角角起辅助作用的

(其实这两个都是接口，比如MethodBeforeAdviceInterceptor， after,throwing都是实现了MethodInterceptor这个接口)

MethodInterceptor有一个方法叫做invoke()
MethodInvocation 有一个proceed() 方法

proceed() 串起了整个Interceptor调用链

## 备注
我标注的几个书签点
1.AOP入口
AbstractAutowireCapableBeanFactory的initializeBean方法的wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);

2.如果有advice就创建代理
AbstractAutoProxyCreator类的wrapIfNecessary方法的 getAdvicesAndAdvisorsForBean和createProxy

3.jdk动态代理调用proceed
JdkDynamicAopProxy类的invoke方法的 invocation.proceed();

4.反射调用目标织入方法
AbstractAspectJAdvice类的invokeAdviceMethodWithGivenArgs方法的 this.aspectJAdviceMethod.invoke(this.aspectInstanceFactory.getAspectInstance(), actualArgs);  
这行代码的底层调用的是Method的invoke方法

# MVC
![](https://image.devilwst.top/imgs/2023/12/7f0cab4c2935d9a7.png)

Dispacher Servlet init方法初始化九大组件

DispatcherServlet 的 doService方法
    getHandler拿到HandlerMapping chain
    getHandlerAdapter(handerMapping)
    adapter.handle() 返回ModleAndView
    最后调用processDispatchResult方法调用view.render()方法通过response输出到浏览器

## 我标注的几个书签点
1. HttpServletBean的init方法 

2. DispatcherServlet的initStrategies方法 初始化九大组件

3. DispatcherServlet的doDispatch


---

# 事物
传播等级
![](https://image.devilwst.top/imgs/2023/12/0074dd37f8a4e696.png)
隔离级别
![](https://image.devilwst.top/imgs/2023/12/370ebefd9ea85a05.png)
执行流程
![](https://image.devilwst.top/imgs/2023/12/b66f59b97c269029.png)






























