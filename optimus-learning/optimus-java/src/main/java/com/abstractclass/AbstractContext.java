package com.abstractclass;

/**
 * Created by li.huan
 * Create Date 2017-04-26 14:21
 */
public abstract class AbstractContext {

    private String name;

    public AbstractContext() {
        System.out.println("父类初始化");
    }

    public AbstractContext(String name) {
        this.name = name;
    }

    public abstract String getBean();





}
