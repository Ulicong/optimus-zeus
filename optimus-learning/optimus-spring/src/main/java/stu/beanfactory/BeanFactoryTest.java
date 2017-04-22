package stu.beanfactory;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * spring Bean加载工厂
 * Created by li.huan.
 * Created on 2017/4/22
 */
public class BeanFactoryTest {

    public static void main(String[] args) throws Throwable {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:config/springMVC-servlet.xml");
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);

        InternalResourceViewResolver controller = beanFactory.getBean("jspViewResolver", InternalResourceViewResolver.class);
        System.out.print(beanFactory.getBeanDefinitionCount() + "\n" + controller.toString());
    }
}
