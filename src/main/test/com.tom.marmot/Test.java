package com.tom.marmot;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述
 *
 * @author : tdl
 * @date : 2019/6/22 下午12:03
 **/
public class Test {

    private static String createAsterisk(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }

    public static String nameEncrypt(String name) {
        if (StringUtils.isEmpty(name) || name.length() < 1) {
            return name;
        }

        return name.replaceAll("(.*)([\\u4e00-\\u9fa5]{1,2})(.*)", "$1" + createAsterisk(name.length() - 1));
    }

    public static void main(String[] args) {
//        System.out.println(PropsUtil.loadProps("marmot.properties", "UTF-8"));
//        System.out.println(ConfigHelper.getJdbcDriver());
//        System.out.println(nameEncrypt("童某了"));
    }
}
