package stu.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import java.lang.reflect.Method;

/**
 * Created by li.huan
 * Create Date 2017-06-21 14:46
 */
public class HandlerMappingDemo extends DefaultAnnotationHandlerMapping {

  //http://blog.csdn.net/zghwaicsdn/article/details/50717334

    private boolean detectHandlersInAncestorContexts = false;

    private static Logger log = LoggerFactory.getLogger(HandlerMappingDemo.class);


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        context.start();
        HandlerMappingDemo bean = context.getAutowireCapableBeanFactory().createBean(HandlerMappingDemo.class);
        bean.detectHandlerByServlet();
    }


    public HandlerMappingDemo() {
    }


    public void detectHandlerByServlet() throws BeansException {
        log.info("Looking for URL mappings in application context: " + getApplicationContext());

        String[] beanNames = (this.detectHandlersInAncestorContexts ?
                BeanFactoryUtils.beanNamesForTypeIncludingAncestors(getApplicationContext(), Object.class) : getApplicationContext().getBeanNamesForType(Object.class));
        // 扫描 rootPackage包下的 Controller
        for (String beanName : beanNames) {
            generateUriPath(beanName);
        }
    }


    public void generateUriPath(String beanName) {
        ApplicationContext context = getApplicationContext();
        Class<?> handlerType = context.getType(beanName);
        String clasName = handlerType.getName();
        Method[] methods = handlerType.getMethods();
        for(Method m:methods) {
            if(m.getName().toLowerCase().contains("$")) {
                System.out.println(m.getName());
            }
        }


        /*RequestMapping mapping = context.findAnnotationOnBean(beanName, RequestMapping.class);
        if (mapping != null) {
            String[] typeLevelPatterns = mapping.value();
            for (String url : typeLevelPatterns) {
               System.out.println(url);
            }
        }*/
    }

}
