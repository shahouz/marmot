package com.tom.marmot.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性操作工具类
 *
 * @author : tdl
 * @date : 2019/6/21 上午10:09
 **/
public class PropsUtil {
    public static Properties loadProps(String propsPath, String encoding) {
        if (StringUtils.isBlank(propsPath)) {
            throw new IllegalArgumentException();
        }

        String suffix = ".properties";
        if (propsPath.lastIndexOf(suffix) == -1) {
            propsPath += suffix;
        }

        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsPath);
        if (is != null) {
            InputStreamReader inputStream = null;
            try {
                inputStream = new InputStreamReader(is, encoding);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            try {
                props.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return props;
    }

    /**
     * 加载属性文件，并转为 Map
     */
    public Map<String, String> loadPropsToMap(Properties props) {
        Map<String, String> map = new HashMap<String, String>(props.stringPropertyNames().size());
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }

        return map;
    }

    /**
     * 获取字符型属性
     */
    public static String getString(Properties props, String key) {
        return props.getProperty(key);
    }

    /**
     * 获取字符型属性（有默认值）
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }

        return value;
    }

    /**
     * 获取数值型属性
     */
    public Integer getInt(Properties props, String key) {
        return getInt(props, key, null);
    }

    public Integer getInt(Properties props, String key, Integer defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            return Integer.parseInt(value.trim());
        }
        return defaultValue;
    }

    /**
     * 获取Long型
     */
    public Long getLong(Properties props, String key) {
        return getLong(props, key, null);
    }

    public Long getLong(Properties props, String key, Long defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            return Long.parseLong(value.trim());
        }
        return defaultValue;
    }

    /**
     * 获取布尔型属性
     */
    public Boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, null);
    }

    public Boolean getBoolean(Properties props, String key, Boolean defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            value = value.toLowerCase().trim();
            if ("true".equals(value)) {
                return true;
            } else if ("false".equals(value)) {
                return false;
            }

            throw new RuntimeException("The value can not parse to Boolean : " + value);
        }

        return defaultValue;
    }

    public boolean containsKey(Properties props, String key) {
        return props.containsKey(key);
    }
}
