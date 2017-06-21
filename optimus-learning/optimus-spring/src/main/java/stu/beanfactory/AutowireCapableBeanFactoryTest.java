package stu.beanfactory;

import com.service.CashService;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by li.huan
 * Create Date 2017-06-21 13:33
 * 我们有时候也会遇到一些脱离spring容器创建的类实例，如何把spring容器内的对象注入到这些类实例内呢。
 * 我们可以用org.springframework.beans.factory.config.AutowireCapableBeanFactory.createBean(Class<?> beanClass, int
 * autowireMode, boolean dependencyCheck) 来创建这个脱离容器的对象，该方法返回脱离容器创建的对象，只不过对象的创建交给spring了。
 * 我们可以用（也许这情况比较多，因为此脱离容器创建的对象也许会有其它框架创建）
 * org.springframework.beans.factory.config.AutowireCapableBeanFactory.autowireBean(Object existingBean)，把this对象传进来。
 */
public class AutowireCapableBeanFactoryTest {

    private CashService cashService;


    public static void main(String[] args) {
        new AutowireCapableBeanFactoryTest().test();
    }

    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        AutowireCapableBeanFactoryTest bean = beanFactory.createBean(AutowireCapableBeanFactoryTest.class);
        // beanFactory.autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        System.out.print(beanFactory.getBean(AutowireCapableBeanFactoryTest.class));
    }


}
