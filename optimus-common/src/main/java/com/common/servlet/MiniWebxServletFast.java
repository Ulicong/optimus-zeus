package com.common.servlet;

import com.common.exception.BizException;
import com.common.exception.LoginTimeoutException;
import com.common.mvc.spring.SpringMvcUtil;
import com.common.mvc.spring.SuffixConstant;
import com.common.mvc.spring.UriHandleMappedUtil;
import com.common.mvc.spring.UriMappedHandler;
import com.common.mvc.spring.UserDefine;
import com.common.util.JsonResult;
import com.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 基于 servlet 容器的 核心类，修改部分代码，用于在 interceptor中可接收 controller 中 annotation参数 快速启动的 sevlet，不依赖于 servlet容器 .<br/>
 * 本servlet 只处理 .json 请求
 */
public class MiniWebxServletFast extends HttpServlet {

    public static final String Spring_Controller_invoke_Method = "SpringServlet_Spring_Controller_invoke_Method";

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

    public static void initCfg() throws ServletException {
        ApplicationContext context = SpringMvcUtil.getContext();

        // 初始化 uri handler映射
        if (webBasePackage != null) {
            webBasePackage = webBasePackage.trim();
        }
        //手动装配外部Bean
        uriHandleMappedUtil = context.getAutowireCapableBeanFactory().createBean(UriHandleMappedUtil.class);
        if (contextPath != null) {
            uriHandleMappedUtil.setContextPath(contextPath);
        }
        uriHandleMappedUtil.setRootPackage(webBasePackage);
        uriHandleMappedUtil.detectHandlerByServlet();
    }

    /**
     * 请求分发
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse res) {
        //getRequestURI()只获取相对路径；getRequestURL()获取全路径
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        long ll = System.currentTimeMillis();
        log.info(uri);

        //打印输入参数
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

            //执行目标方法
            Object result = uriHandleMappedUtil.invokeHandlerMethod(handler, handler.getHandleMethod(), handler.getHandleObject(), req, res);

            // 渲染页面
            renderView(mav, result, handler, req, res);

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


    private static void outJson(JsonResult obj, String[] excludes, final HttpServletRequest request, HttpServletResponse response,
                                UriMappedHandler handler) {
        String outMsg = "";
        try {
            outMsg = JsonUtil.toJson(obj);
            if (response != null) {
                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("utf-8");

                if (request != null) {
                    String output = null;
                    if (request.getParameter("_callback_var") != null) {
                        output = "var " + request.getParameter("_callback_var") + "=" + outMsg + ";";
                    } else if (request.getParameter("_callback_jsonp") != null) {
                        output = request.getParameter("_callback_jsonp") + "(" + outMsg + ");";
                    } else {
                        output = outMsg;
                    }
                    response.getWriter().write(output);
                } else {
                    response.getWriter().write(outMsg);
                }
            }
        } catch (Exception e) {
            JsonResult jr = new JsonResult();
            jr.setSuccess(false);
            jr.setErrorMsg(e.getMessage());
            jr.setErrorCode("500");
            try {
                response.getWriter().write(JsonUtil.toJson(jr));
            } catch (Exception e2) {
            }
            log.error("json输出出错：", e);
        } finally {
            if (obj.getSuccess()) {
                log.info("response data: " + outMsg);
            } else {
                log.warn("response data: " + outMsg);
            }
        }
    }

    private void renderView(ModelAndView mv, Object result, UriMappedHandler handler, HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        // mv不为空，说明是直接访问.vm 页面，不经过 screen类
        if (mv != null) {
            render(mv, request, response);
            return;
        }

        String uri = request.getRequestURI();
        uri = uri.substring(request.getContextPath().length());
        mv = null;

        if (handler.getIsRewrite()) {
            uri = uri.substring(0, uri.lastIndexOf("/")) + uri.substring(uri.lastIndexOf("."));
        }

        // 默认根路径请求
        if (uri.equals("/")) {
            String path = "screen/index";

            mv = new ModelAndView();
            mv.setViewName(path);

            render(mv, request, response);
        }
        // screen 页面请求
        else if (uri.endsWith(SuffixConstant.htmSuffixt)) {
            String path = uri.substring(contextPath.length(), uri.length() - SuffixConstant.htmSuffixt.length());
            mv = new ModelAndView();
            mv.setViewName(path);

            render(mv, request, response);
        }
        // json 格式请求
        else if (uri.endsWith(SuffixConstant.jsonSuffix)) {
            if (handler.getHandleMethod().isAnnotationPresent(UserDefine.class)) {
                return;
            }
            JsonResult jr = new JsonResult(result);
            outJson(jr, null, request, response, handler);
        }
    }

    /**
     * 渲染普通页面
     *
     * @param mv
     * @param request
     * @param response
     */
    private void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
        try {
           /* response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("utf-8");

            String vmData = vmCache.get(mv.getViewName());
            if (vmData == null) {
                java.io.InputStream is = ConfigLoader.loadResource(vmLoaderPath + mv.getViewName() + ".vm", false, false);
                if (is == null) {
                    // response.getOutputStream().write("404 page not found".getBytes());
                    return;
                }
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[5000];
                int len = -1;
                while ((len = is.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }

                is.close();
                vmData = new String(bos.toByteArray(), "utf-8");
                Boolean velocity_template_cache = Configs.getConfig("velocity_template_cache") != null ? Boolean.valueOf(Configs.getConfig("velocity_template_cache")) : false;
                if (velocity_template_cache) {
                    vmCache.put(mv.getViewName(), vmData);
                }
            }

            CharArrayWriter writer = new CharArrayWriter();

            request.setAttribute("util", util);
            Velocity.evaluate(MvcVelocityHelper.convertVelocityContext(request), writer, "", vmData);

            response.getOutputStream().write(writer.toString().getBytes("utf-8"));
*/
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    public static void setContext(ApplicationContext context) {
        MiniWebxServletFast.context = context;
    }

    public static void setWebBasePackage(String webBasePackage) {
        MiniWebxServletFast.webBasePackage = webBasePackage;
    }

    public static void setContextPath(String contextPath) {
        MiniWebxServletFast.contextPath = contextPath;
    }

    public static void setUriHandleMappedUtil(UriHandleMappedUtil uriHandleMappedUtil) {
        MiniWebxServletFast.uriHandleMappedUtil = uriHandleMappedUtil;
    }
}
