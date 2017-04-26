package com.abstractclass;

/**
 * Created by li.huan
 * Create Date 2017-04-26 14:21
 */
public abstract class AbstractContext {

    private String name;

    public AbstractContext() {
        this(null);
    }

    public AbstractContext(String name) {
        this.name = name;
    }

    public abstract String getBean();
}
