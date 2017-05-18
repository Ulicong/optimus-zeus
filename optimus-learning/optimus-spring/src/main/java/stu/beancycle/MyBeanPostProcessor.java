package stu.beancycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 用于自定义bean实例化前后动作
 * Created by li.huan
 * Create Date 2017-04-24 16:59
 */
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("car")) {
            Car car = (Car) bean;
            if (car.getColor() == null) {
                System.out.print("调用BeanPostProcessor.postProcessBeforeInitialization;color为空，设置color为黑色.\n");
                car.setColor("黑色");
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("car")) {
            Car car = (Car) bean;
            if (car.getMaxSpeed() >= 200) {
                System.out.print("调用BeanPostProcessor.postProcessAfterInitialization;将maxSpeed设置成200\n");
                car.setMaxSpeed(200);
            }
        }
        return bean;
    }
}
