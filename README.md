# marmot

#### 介绍
轻量级MVC框架。

## 注解

- @Controller 声明控制器Bean
- @Service 声明服务层Bean
- @RequestMapping 声明接口
- @Inject 注入
- @Aspect 声明切面

## AOP的使用

使用例子可参考com.tom.marmot.test.ControllerAspect

#### 关于IOC

通过BeanHelper类（相当于IOC容器）获取已经加入到容器中的实例。（其中也包含用@Service注解的类）
遍历并反射，找到类中存在@Inject的属性，并查找Inject对应的实现类。
通过InjectUtil.getServiceResource()方法，在@Service注解过的类中查找与@Inject匹配的类（匹配规则就是@Service的类需要实现@Inject注解的属性类型所指向的类）