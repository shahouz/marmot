package com.tom.marmot.helper;

import com.tom.marmot.annotation.Inject;
import com.tom.marmot.util.InjectUtil;
import com.tom.marmot.util.ReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * 依赖注入助手
 *
 * @author : tdl
 * @date : 2019/6/25 下午12:21
 **/
public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (beanMap != null && beanMap.size() > 0) {
            for (Map.Entry<Class<?>, Object> beanEntity : beanMap.entrySet()) {
                Class<?> beanClass = beanEntity.getKey();
                Object beanInstance = beanEntity.getValue();

                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> sourceClass = InjectUtil.getServiceResource(beanField);
                            Object beanFieldInstance = beanMap.get(sourceClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
