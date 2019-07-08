package com.tom.marmot.loader;

import com.tom.marmot.helper.*;
import com.tom.marmot.util.ClassUtil;

/**
 * 助手类初始化加载器
 *
 * @author : tdl
 * @date : 2019/6/27 上午10:29
 **/
public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
