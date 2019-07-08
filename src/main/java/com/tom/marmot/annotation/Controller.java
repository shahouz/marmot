package com.tom.marmot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器类注解
 *
 * @author : tdl
 * @date : 2019/6/23 下午5:29
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    /**
     * 控制器名称 - 用户生成文档
     */
    String name() default "";
}
