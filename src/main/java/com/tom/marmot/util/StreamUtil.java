package com.tom.marmot.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 *
 * @author : tdl
 * @date : 2019/6/27 上午10:55
 **/
public class StreamUtil {

    /**
     * 解析输入流转为字符串
     *
     * @param is 流
     * @return 字符串
     */
    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
