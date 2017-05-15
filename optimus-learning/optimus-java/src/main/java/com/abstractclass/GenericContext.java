package com.abstractclass;

/**
 * Created by li.huan
 * Create Date 2017-04-26 14:23
 */
public class GenericContext extends AbstractContext {


    public GenericContext() {
        super();
    }

    public GenericContext(String name) {
        this();
    }

    @Override
    public String getBean() {
        return "GenericContext-Bean";
    }
}
