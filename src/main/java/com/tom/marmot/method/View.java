package com.tom.marmot.method;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图出参对象
 *
 * @author : tdl
 * @date : 2019/6/27 上午10:50
 **/
public class View {
    private String path;

    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
