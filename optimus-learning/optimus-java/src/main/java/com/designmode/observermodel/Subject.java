package com.designmode.observermodel;

/**
 * 被观察者抽象
 * Created by li.huan
 * Create Date 2017-04-25 16:52
 */
public interface Subject {

    //注册观者
    void register(Observer obj);

    //卸载观察者
    void unRegister(Observer obj);

    //通知观察者
    void notifyObserver();

    Object getUpdate(Observer obj);
}
