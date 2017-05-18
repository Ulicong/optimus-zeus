package stu.beancycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * postProcessor 初始化方法调用类
 * Created by li.huan
 * Create Date 2017-05-18 10:33
 */
@Service
public class MyBeanFactoryPostProcessorInit {

    @Autowired
    MyBeanFactoryPostProcessor postProcessor;

    @PostConstruct
    public void init() {
        postProcessor.init();
    }
}
