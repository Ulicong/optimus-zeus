package com.common.jetty;

import com.common.exception.BizException;
import com.common.mvc.spring.SpringMvcUtil;
import com.common.servlet.MiniWebxServletFast;
import com.common.util.Configs;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.LocalConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by weber on 2017/4/16.
 * Jetty启动类
 */
public class RestApiServer {

    /**
     * 启动Jetty服务
     */
    public static void start() {
        String contextPath = "/"+Configs.getConfig("dubbo.application.name");
        new RestApiServer().startServer(contextPath);
    }

    //spring 配置文件
    private final String[] springXmls = new String[]{"config/spring-view.xml"};

    private Logger log;

    private RestApiServer() {
        log = LoggerFactory.getLogger(RestApiServer.class);
    }


    public void startServer(String contextPath) {
        Integer httpPort = -1;
        try {
            String config = Configs.getConfig("rest.api.port");
            httpPort = Integer.valueOf(config == null ? "" : config);
            if (httpPort == null || httpPort < 0) {
                throw new BizException("缺少 rest.api.port 配置！");
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new BizException("rest.api.port 配置不合法！");
        }

        long startTime = System.currentTimeMillis();

        /**启动spring**/
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springXmls);
            context.start();
            SpringMvcUtil.setContext(context);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        try {
            MiniWebxServletFast.setWebBasePackage("com.optimus.module");
            MiniWebxServletFast.setContextPath(contextPath);

            /**jetty 配置 启动**/
            org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(httpPort);
            LocalConnector connector = new LocalConnector(server);
            connector.setIdleTimeout(1 * 60000);
            HttpConfiguration httpConfiguration = connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration();
            httpConfiguration.setSendServerVersion(false);
            httpConfiguration.setSendDateHeader(false);
            ServletContextHandler servletContextHandler = new ServletContextHandler();
            servletContextHandler.setMaxFormContentSize(Integer.MAX_VALUE); // form 表单数据最大值

            MiniWebxServletFast.initCfg();

            ServletHolder servletHolder = new ServletHolder(MiniWebxServletFast.class);
            servletContextHandler.addServlet(servletHolder, "*.json,*.htm");
            server.setHandler(servletContextHandler);
            server.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("rest服务监听端口：" + httpPort);
        long endTime = System.currentTimeMillis();
        log.info("server start in {} ms", endTime - startTime);
    }


}
