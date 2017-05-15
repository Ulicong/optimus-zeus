package stu.beancycle;

/**
 * Spring注解汇总
 * 使用规则、尽量各层用相应注解
 * Created by li.huan
 * Create Date 2017-05-15 16:11
 */
public enum BeanAnnotation {
    /**
     * 普通组件
     */
    Component,
    /**
     * spring支持java原生注解
     */
    Resource,
    /**
     * DAO层定义
     */
    Repository,
    /**
     * 用于接口实现类
     */
    Service,
    /**
     * Mvc控制类
     */
    Controller,
    /**
     * 依赖注入
     */
    Autowired,
    /**
     * 如果容器中存在一个以上的Bean
     * 则用此注解指定Bean的名字进行依赖注入
     */
    Qualifier

}
