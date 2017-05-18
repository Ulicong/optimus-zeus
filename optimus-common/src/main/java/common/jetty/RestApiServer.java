package common.jetty;

import common.exception.BizException;
import common.mvc.spring.SpringMvcUtil;
import common.servlet.MiniWebxServletFast;
import common.util.Configs;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
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
        String contextPath = Configs.getConfig("dubbo.application.name");
        new RestApiServer().startServer(contextPath);
    }

    //spring 配置文件
    private final String[] springXmls = new String[]{"config/applicationContext.xml","config/springMVC-servlet.xml"};

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

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springXmls);
        context.start();
        SpringMvcUtil.setContext(context);

        //启动Jetty服务
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(httpPort);
        connector.setMaxIdleTime(10000);

        MiniWebxServletFast.webBasePackage = "zeus.optimus.module";
        MiniWebxServletFast.contextPath = contextPath;

        ServletHolder pageHolder = new ServletHolder(MiniWebxServletFast.class);
        pageHolder.setInitOrder(1);

        Context root = new Context();
        root.addServlet(pageHolder, "/*");

        Server server = new Server();
        server.addConnector(connector);
        server.setHandler(root);

        try {
            server.start();
            log.info("rest服务监听端口：" + httpPort);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        log.info("server start in {} ms", endTime - startTime);
    }


}
