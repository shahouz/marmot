package com.tom.marmot.test;

import com.tom.marmot.annotation.Controller;
import com.tom.marmot.annotation.Inject;
import com.tom.marmot.annotation.RequestMapping;
import com.tom.marmot.method.Param;

import java.util.Map;
import java.util.Set;

/**
 * 测试用的控制器
 *
 * @author : tdl
 * @date : 2019/6/27 下午1:42
 **/
@Controller
public class TestController {
    @Inject
    private TestService testService;

    @RequestMapping(value = "/test")
    public String testAction(Param param) {
        if(testService == null) {
            return "注入失败";
        }

        if(!testService.checkExists()) {
            return "不存在";
        }

        Map<String, Object> params = param.getMap();
        Set<String> keySet = params.keySet();
        StringBuilder builder = new StringBuilder();
        for (String key : keySet) {
            builder.append("I am ").append(params.get(key)).append(". ");
        }

        return builder.toString();
    }

    @RequestMapping(value = "/")
    public String indexAction(Param param) {
        return "sweet home.";
    }

    @RequestMapping(method = "post", value = "/")
    public String postAction(Param param) {
        return "I am poster.";
    }
}
