package com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Service;

/**
 * Created by li.huan
 * Create Date 2017-05-18 12:36
 */
@Service
public class Tset implements BeanDefinitionRegistryPostProcessor {

    ConfigurableListableBeanFactory factory;

    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        this.factory = factory;

    }

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry arg0) throws BeansException {
    }

}
