package stu.beanfactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebApplicationContext
 * 加载web项目根路径下的配置文件
 * 依赖Servlet容器如Tomcat、Jetty
 * <p>
 * Created by li.huan.
 * Created on 2017/4/23
 * <p>
 * Sring的Jetty容器启动过程
 * <p>
 * (1）创建WebAppContext对象，设置它的contextPath，资源文件路径啥的。
 * （2）创建配置类对象。。。以及当前context的classLoader。。。
 * （3）解压当前的程序war包什么的，并用classLoader来载入里面的源文件jar包什么的。。
 * （4）处理webdefault.xml，例如里面声明的默认servlet，filter啥的。。
 * （5）处理用户的webinf里面的web.xml（默认是它），根据里面的servlet，listener啥的定义，创建相应的servletholder啥的。。。。
 * （6）调用contextListener的contextInitialized方法，接着还要获取用户定义的initParams，将他们保存在当前servletContext里面去。。
 * （7）初始化servletHandler，例如从classLoader里面获取servlet的class文件，如果配置了startup的话，还需要立即创建servlet对象，而且调用init方法。。
 */
@Controller
@RequestMapping
public class WebApplicationContextTest {


    @RequestMapping("/webApplicationContext")
    public void testWebApplicationContext(HttpServletRequest request, HttpServletResponse response) {
        ServletContext servletContext = request.getSession().getServletContext();
        //  WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        WebApplicationContext context = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        System.out.print(context.toString());
    }
}
