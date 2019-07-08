package com.tom.marmot.util;

import com.tom.marmot.helper.ClassHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * 描述
 *
 * @author : tdl
 * @date : 2019/7/8 上午10:41
 **/
public class InjectUtil {

    /**
     * 获取注入的实现类
     *
     * @param
     * @return
     * @throws
     */
    public static Class<?> getServiceResource(Field field) {
        Set<Class<?>> classSet = ClassHelper.getServiceClassSet();
        Class<?> beanFieldClass = field.getType();
        String className = beanFieldClass.getName();
        if (CollectionUtils.isEmpty(classSet)) {
            return null;
        }

        for (Class<?> cls : classSet) {
            if (cls == null) {
                continue;
            }

            Class<?>[] interfaces = cls.getInterfaces();

            if (ArrayUtils.isNotEmpty(interfaces)) {
                for (Class<?> interfaceClass : interfaces) {
                    if (interfaceClass == null) {
                        continue;
                    }

                    // 查找service注解类中，是否有实现指定接口的
                    if (className.equals(interfaceClass.getName())) {
                        return cls;
                    }
                }
            }
        }

        return null;
    }
}
