package com.designmode.observermodel;

/**
 * 观察者接口抽象
 * <p>
 * Created by li.huan
 * Create Date 2017-04-25 16:53
 */
public interface Observer {

    //观察者更新动作
     void update();

     void setSubject(Subject obj);
}
