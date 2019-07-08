package com.tom.marmot.annotation;

import java.lang.annotation.*;

/**
 * AOP
 *
 * @author : tdl
 * @date : 2019/7/4 上午11:39
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
