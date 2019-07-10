# marmot

## 介绍
轻量级MVC框架。

### 注解

- @Controller 声明控制器Bean
- @Service 声明服务层Bean
- @RequestMapping 声明接口
- @Inject 注入
- @Aspect 声明切面
- @Transaction 事务支持

### AOP的使用

新增一个类，继承`AspectProxy`类，可重写 `begin、end、error、after、before` 等方法用于定义Advice；
在类上加`@Aspect`注解，设置value值用于配置Pointcut；
使用例子可参考com.tom.marmot.test.ControllerAspect。

### 关于IOC

通过BeanHelper类（相当于IOC容器）获取已经加入到容器中的实例。（其中也包含用@Service注解的类）
遍历并反射，找到类中存在@Inject的属性，并查找Inject对应的实现类。
通过InjectUtil.getServiceResource()方法，在@Service注解过的类中查找与@Inject匹配的类（匹配规则就是@Service的类需要实现@Inject注解的属性类型所指向的类）

### 关于事务

在需要支持事务的方法上添加@Transaction，当方法抛出Exception异常时会自动进行事务回滚；
可查看例子com.tom.marmot.test.TestServiceImpl。