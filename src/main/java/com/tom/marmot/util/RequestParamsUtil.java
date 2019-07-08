package com.tom.marmot.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP请求参数操作工具类
 *
 * @author : tdl
 * @date : 2019/6/27 下午8:03
 **/
public class RequestParamsUtil {
    public static Map<String, Object> getQuery(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return getQuery(request, paramMap);
    }

    public static Map<String, Object> getQuery(HttpServletRequest request, Map<String, Object> paramMap) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }

        return paramMap;
    }

    public static Map<String, Object> getBody(HttpServletRequest request, Map<String, Object> paramMap) throws IOException {
        String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
        if (StringUtils.isNotEmpty(body)) {
            String[] params = StringUtils.split(body, "&");
            if (ArrayUtils.isNotEmpty(params)) {
                for (String param : params) {
                    String[] array = StringUtils.split(param, "=");
                    if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                        String paramName = array[0];
                        String paramValue = array[1];
                        paramMap.put(paramName, paramValue);
                    }
                }
            }
        }

        return paramMap;
    }
}
