package com.tom.marmot.proxy;

import java.lang.reflect.Method;


/**
 * 切面代理
 * 在执行流程中提前放入钩子，需要使用钩子时，只需要继承该类重写对应方法。
 *
 * @author : tdl
 * @date : 2019/7/4 上午11:49
 **/
public class AspectProxy implements Proxy {
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params, result);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            error(e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    public void begin() {
    }

    public void end() {
    }

    public void error(Exception e) {
    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }
}
