package stu.beanfactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContext是有BeanFactory派生出来的
 * <p>
 * Created by li.huan.
 * Created on 2017/4/22
 */
public class ApplicationContextTest {

    public static void main(String[] args) {
        String[] xmlPath = new String[]{"classpath:config/springMVC-servlet.xml",
                "classpath:config/applicationContext.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        context.start();
    }

}
