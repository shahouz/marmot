package com.tom.marmot.test;

import com.tom.marmot.annotation.Aspect;
import com.tom.marmot.annotation.Controller;
import com.tom.marmot.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * 用于拦截控制器方法
 *
 * @author : tdl
 * @date : 2019/7/4 上午11:51
 **/
@Aspect(value = Controller.class)
public class ControllerAspect extends AspectProxy {
    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        System.out.println("after");
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("before");
    }
}
