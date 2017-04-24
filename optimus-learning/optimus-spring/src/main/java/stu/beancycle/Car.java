package stu.beancycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by li.huan.
 * Created on 2017/4/23
 */
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String brand;
    private String color;
    private int maxSpeed;

    private BeanFactory factory;
    private String beanName;

    public Car() {
        System.out.print("调用Card()构造函数\n");
    }

    public void setBrand(String brand) {
        this.brand = brand;
        System.out.print("调用setBrand()设置属性\n");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.print("调用BeanFactoryAware.setBeanFactory()\n");
        this.factory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("调用InitializingBean.afterPropertiesSet()\n");
    }

    @Override
    public void destroy() throws Exception {
        System.out.print("调用DisposableBean.destroy()\n");
    }

    public void myInit() {
        System.out.print("调用my-init指定的myInit()方法\n");
        this.maxSpeed = 240;
    }

    public void myDestroy() {
        System.out.print("调用destroy-method指定的myDestroy()方法\n");
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.print("调用BeanNameAware的setBeanName()方法\n");
        this.beanName = beanName;
    }


    public void disPlayInfo(){
        System.out.print("brand:"+brand+"color:"+color+"maxSpeed:"+maxSpeed+"\n");

    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}



