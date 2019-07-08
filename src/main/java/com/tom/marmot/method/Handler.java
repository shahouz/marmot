package com.tom.marmot.method;

import java.lang.reflect.Method;

/**
 * 描述
 *
 * @author : tdl
 * @date : 2019/6/25 下午8:07
 **/
public class Handler {

    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
