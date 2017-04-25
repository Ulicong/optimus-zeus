package stu.beanfactory;

import com.service.CashService;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Map;

/**
 * spring Bean加载工厂
 * Created by li.huan.
 * Created on 2017/4/22
 */
public class BeanFactoryTest {

    public static void main(String[] args) throws Throwable {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:config/applicationContext.xml");

        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);

        //InternalResourceViewResolver controller = beanFactory.getBean("jspViewResolver", InternalResourceViewResolver.class);
        //System.out.print(beanFactory.getBeanDefinitionCount() + "\n" + controller.toString());

        //获取接口cashService下面的所有子类Bean
        Map<String, CashService> beans = beanFactory.getBeansOfType(CashService.class);
        for (Map.Entry<String, CashService> entry : beans.entrySet()) {
            CashService value = entry.getValue();
            System.out.print(value + "\n");
        }

    }
}
