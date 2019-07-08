package com.tom.marmot.method;

import com.tom.marmot.util.CastUtil;

import java.util.Map;

/**
 * 入参对象
 *
 * @author : tdl
 * @date : 2019/6/27 上午10:46
 **/
public class Param {
    private Map<String, Object> paramMap;

    public Param() {
    }

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getMap() {
        return paramMap;
    }
}
