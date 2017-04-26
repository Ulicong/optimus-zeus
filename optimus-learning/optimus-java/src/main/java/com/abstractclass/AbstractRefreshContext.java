package com.abstractclass;

/**
 * Created by li.huan
 * Create Date 2017-04-26 14:30
 */
public abstract class AbstractRefreshContext extends AbstractContext{

    public AbstractRefreshContext(String name){
        super(name);
    }


    @Override
    public String getBean() {
        return "AbstractRefreshContext-Bean";
    }
}
