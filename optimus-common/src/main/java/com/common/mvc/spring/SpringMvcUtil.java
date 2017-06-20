package com.common.mvc.spring;

import com.common.servlet.MiniWebxServletFast;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 *
 */
public class SpringMvcUtil {

    private static ApplicationContext context;

    /**
     * 获取 beanfactory 对象
     *
     * @return
     */
    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 获取 controll screen 目标执行方法
     *
     * @param request
     * @return
     */
    public static Method getHandlerMethod(HttpServletRequest request) {
        return (Method) request.getAttribute(MiniWebxServletFast.Spring_Controller_invoke_Method);
    }

    public static void setContext(ApplicationContext context) {
        SpringMvcUtil.context = context;
    }

    public synchronized static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

}
