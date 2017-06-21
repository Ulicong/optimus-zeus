package stu.beancycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Service;

/**
 * 用于改变Bean上下文
 *
 * Created by li.huan
 * Create Date 2017-05-17 18:09
 */
@Service
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    ConfigurableListableBeanFactory factory;

    /**
     * 由于本bean实现了BeanDefinitionRegistryPostProcessor接口故在容器初始化invokeBeanFactoryPostProcessors()时已经初始化；
     * 并没有在容器生命周期最后一步finishBeanFactoryInitialization(beanFactory);
     * 时行初始化、所以此方法即便加上@PostConstruct也无法执行;所以定义MyBeanFactoryPostProcessorInit类进行初始化调用
     */
    public void init() {
        MyBeanFactoryPostProcessor bean = factory.getBean(MyBeanFactoryPostProcessor.class);
        System.out.println(bean.toString());
    }


    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        factory = beanFactory;
    }

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }
}
