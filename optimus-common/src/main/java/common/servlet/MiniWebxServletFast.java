package common.servlet;

import common.exception.BizException;
import common.exception.LoginTimeoutException;
import common.mvc.spring.SpringMvcUtil;
import common.mvc.spring.UriHandleMappedUtil;
import common.mvc.spring.UriMappedHandler;
import common.util.JsonResult;
import common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 基于 servlet 容器的 核心类，修改部分代码，用于在 interceptor中可接收 controller 中 annotation参数 快速启动的 sevlet，不依赖于 servlet容器 .<br/>
 * 本servlet 只处理 .json 请求
 */
public class MiniWebxServletFast extends HttpServlet {

    private static final String Spring_Controller_invoke_Method = "SpringServlet_Spring_Controller_invoke_Method";

    private static ApplicationContext context = null;

    private static Logger log = LoggerFactory.getLogger(MiniWebxServletFast.class);

    public static String webBasePackage;

    public static String contextPath;

    private static UriHandleMappedUtil uriHandleMappedUtil;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = SpringMvcUtil.getContext();

        // 初始化 uri handler映射 TODO
        if (webBasePackage == null) {
            throw new BizException("webBasePackage is null");
        }
        //手动装配外部Bean
        uriHandleMappedUtil = context.getAutowireCapableBeanFactory().createBean(UriHandleMappedUtil.class);
        if (contextPath != null) {
            uriHandleMappedUtil.setContextPath(contextPath);
        }
        uriHandleMappedUtil.setRootPackage(webBasePackage);
        uriHandleMappedUtil.detectHandlerByServlet();

      /*  // 初始化 interceptors TODO
        if (interceptors == null) {
            Field interceptorField = ReflectionUtils.findField(AbstractHandlerMapping.class, "interceptors");
            ReflectionUtils.makeAccessible(interceptorField);
            interceptors = (List<Object>) ReflectionUtils.getField(interceptorField, context.getBean(AbstractHandlerMapping.class));
        }*/
    }

    /**
     * 请求分发
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse res) {
        //getRequestURI()只获取相对路径；getRequestURL()获取全路径
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        long ll = System.currentTimeMillis();
        log.info(uri);

        //打印输入参数 TODO
        if (log.isInfoEnabled()) {
            StringBuilder sb = new StringBuilder();
            Enumeration<String> en = req.getParameterNames();
            while (en.hasMoreElements()) {
                String key = en.nextElement();
                String[] values = req.getParameterValues(key);
                sb.append(key);
                sb.append("=");
                if (values != null && values.length <= 1) {
                    sb.append(values[0]);
                } else {
                    sb.append(Arrays.toString(values));
                }
                sb.append(",");
            }
            String outLog = sb.toString();
            if (outLog.endsWith(",")) {
                outLog = outLog.substring(0, outLog.length() - ",".length());
            }
            log.info("request uri: " + uri);
            log.info("request params: " + outLog);
        }

        int interceptorIndex = -1;
        Object handlerObject = null;
        ModelAndView mav = null;

        try {
            //根据uri获取对应 handler
            UriMappedHandler handler = uriHandleMappedUtil.getMappedHandler(uri);
            if (handler == null) {
                handler = uriHandleMappedUtil.getMappedRewriteHandler(uri);
            }
            if (handler != null) {
                handlerObject = handler.getHandleObject();
                req.setAttribute(MiniWebxServletFast.Spring_Controller_invoke_Method, handler.getHandleMethod());
            } else {
                throw new ServletException(uri + " 404");
            }

           /* // 执行拦截器 preHandle TODO
            for (Object object : interceptors) {
                if (object instanceof HandlerInterceptor) {
                    HandlerInterceptor interceptor = (HandlerInterceptor) object;
                    if (!interceptor.preHandle(request, response, handlerObject)) {
                        break;
                    }
                }
                interceptorIndex++;
            }
            */

            //执行目标方法
            Object result = uriHandleMappedUtil.invokeHandlerMethod(handler, handler.getHandleMethod(), handler.getHandleObject(), req, res);

            /*// 执行拦截器 postHandle TODO
            if (interceptors != null) {
                for (int i = interceptors.size() - 1; i >= 0; i--) {
                    HandlerInterceptor interceptor = (HandlerInterceptor) interceptors.get(i);
                    interceptor.postHandle(request, response, handlerObject, null);
                }
            }*/

        } catch (Throwable ex) {
            if (ex instanceof InvocationTargetException) {
                ex = ((InvocationTargetException) ex).getTargetException();
            }
            renderExceptionView(req, res, ex, null, handlerObject != null);
            if (ex instanceof BizException || ex instanceof LoginTimeoutException || ex instanceof ServletException) {
                // 这些异常不需要输出日志
                if (ex instanceof BizException) {
                    log.warn(ex.getMessage());
                }
            } else {
                log.error(ex.getMessage(), ex);
            }

        } finally {
          /*  // 拦截器收尾 TODO
            if (interceptors != null) {
                for (int i = interceptorIndex; i >= 0; i--) {
                    HandlerInterceptor interceptor = (HandlerInterceptor) interceptors.get(i);
                    try {
                        interceptor.afterCompletion(request, response, handlerObject, null);
                    } catch (Throwable ex2) {
                        log.error("HandlerInterceptor.afterCompletion threw exception", ex2);
                    }
                }
            }*/
        }

        long l2 = System.currentTimeMillis();
        if (log.isInfoEnabled()) {
            log.info("execute[" + uri + "]spend time : " + (l2 - ll));
        }
    }


    /**
     * 根据异常类型-渲染异常页面
     */
    private void renderExceptionView(HttpServletRequest req, HttpServletResponse res, Throwable ex, Error err, Boolean hasHandler) {

    }


    /**
     * 输出Josn
     */
    private static void outJson(Object obj, String[] excludes, HttpServletResponse response, HttpServletRequest request) {
        String outMsg = "";
        try {
            PrintWriter writer = response.getWriter();
            outMsg = JsonUtil.toJson(obj);
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            if (request != null) {
                String output;
                if (request.getParameter("_callback_var") != null) { // 兼容 jsonp 请求
                    output = "var " + request.getParameter("_callback_var") + "=" + outMsg + ";";
                } else if (request.getParameter("_callback_jsonp") != null) {
                    output = request.getParameter("_callback_jsonp") + "(" + outMsg + ");";
                } else {
                    output = outMsg;
                }
                writer.write(output);
            } else {
                writer.write(outMsg);
            }
            writer.close();
        } catch (Exception e) {
            JsonResult jr = new JsonResult();
            jr.setSuccess(false);
            jr.setErrorMsg(e.getMessage());
            jr.setErrorCode("500");
            try {
                response.getWriter().write(JsonUtil.toJson(jr));
            } catch (Exception e2) {
                log.error("json输出出错：", e2);
            }
            log.error("json输出出错：", e);
        } finally {
            log.info("输出JSON: " + outMsg);
        }
    }

}
