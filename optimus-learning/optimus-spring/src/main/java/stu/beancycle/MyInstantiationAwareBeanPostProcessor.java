package stu.beancycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * Created by li.huan.
 * Created on 2017/4/23
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    /**
     * 在实例化Bean之前调用
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.print("InstantiationAware BeanPostProcessorAdapter BeforeInitialization.\n");
        }

        return bean;
    }


    /**
     * 在实例化Bean之后调用
     */
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.print("InstantiationAware BeanPostProcessorAdapter AfterInstantiation.\n");
        }

        return true;
    }

    /**
     * 在设置Bean的某一个属性时调用
     */
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.print("InstantiationAware BeanPostProcessorAdapter ProcessPropertyValues.\n");
        }

        return pvs;
    }
}
