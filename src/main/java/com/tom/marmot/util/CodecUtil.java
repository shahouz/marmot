package com.tom.marmot.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLEncode工具类
 *
 * @author : tdl
 * @date : 2019/6/27 上午10:58
 **/
public class CodecUtil {
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return target;
    }

    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return target;
    }
}
