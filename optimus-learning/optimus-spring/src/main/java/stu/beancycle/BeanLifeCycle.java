package stu.beancycle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 、测试入口类
 * <p>
 * Created by li.huan
 * Create Date 2017-04-24 17:14
 */
public class BeanLifeCycle {

    private static void lifeCycleBeaFactory() {

        ClassPathResource resource = new ClassPathResource("stu/beancycle/spring-beans.xml");
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);

        ((ConfigurableBeanFactory) xmlBeanFactory).addBeanPostProcessor(new MyBeanPostProcessor());
        ((ConfigurableBeanFactory) xmlBeanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        Car car = (Car) xmlBeanFactory.getBean("car");
        car.disPlayInfo();
        car.setColor("红色");

        //第二次从缓存中取
        Car car2 = (Car) xmlBeanFactory.getBean("car");
        System.out.print("car == car2:" + (car == car2) + "\n");

        ((XmlBeanFactory) xmlBeanFactory).destroySingletons();
    }


    public static void main(String[] args) {
        lifeCycleBeaFactory();
    }

}
