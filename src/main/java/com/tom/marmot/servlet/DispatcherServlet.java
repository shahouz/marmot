package com.tom.marmot.servlet;

import com.alibaba.fastjson.JSON;
import com.tom.marmot.helper.BeanHelper;
import com.tom.marmot.helper.ConfigHelper;
import com.tom.marmot.helper.ControllerHelper;
import com.tom.marmot.loader.HelperLoader;
import com.tom.marmot.method.Handler;
import com.tom.marmot.method.Param;
import com.tom.marmot.method.View;
import com.tom.marmot.util.CodecUtil;
import com.tom.marmot.util.RequestParamsUtil;
import com.tom.marmot.util.ReflectionUtil;
import com.tom.marmot.util.StreamUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求处理器
 *
 * @author : tdl
 * @date : 2019/6/27 上午11:49
 **/
public class DispatcherServlet extends HttpServlet {
    private final static String ROOT_PATH = "/";

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求来了\n");
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getServletPath();
        // 获取Action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            Map<String, Object> paramMap = new HashMap<String, Object>(16);

            // 解析Query传参
            paramMap = RequestParamsUtil.getQuery(request, paramMap);

            // 解析Body传参
            paramMap = RequestParamsUtil.getBody(request, paramMap);

            Method actionMethod = handler.getActionMethod();
            Param param = new Param(paramMap);
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);

            // 返回的是视图
            if (result instanceof View) {
                View view = (View) result;
                String path = view.getPath();
                if (StringUtils.isNotEmpty(path)) {
                    if (path.startsWith(ROOT_PATH)) {
                        response.sendRedirect(request.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }

                        request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
                    }
                }

            }
            // 返回的是数据
            else {
                String data = (String) result;
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                String json = JSON.toJSONString(data);
                writer.write(json);
                writer.flush();
                writer.close();
            }
        }
    }
}
