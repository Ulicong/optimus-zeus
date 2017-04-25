package com.designmode.observermodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察都实现类
 *
 * Created by li.huan
 * Create Date 2017-04-25 17:00
 */
public class MyTopic implements Subject {
    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public MyTopic() {
        this.observers = new ArrayList<>();
    }

    /**
     * 注册除观察者
     *
     * @param obj 观察者
     */
    public void register(Observer obj) {
        if (obj == null) {
            throw new NullPointerException("Null Observer");
        }
        if (!observers.contains(obj)) observers.add(obj);
    }

    public void unRegister(Observer obj) {
        observers.remove(obj);
    }

    public Object getUpdate(Observer obj) {
        return this.message;
    }

    /**
     * notifyObservers()中使用synchronization同步的方式来确保在消息被发布给主题之前，通知只能被发送到注册的观察者处。
     */
    public void notifyObserver() {
        synchronized (MUTEX) {
            List<Observer> observersLocal = null;
            if (!changed) return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void postMessage(String msg) {
        System.out.print("Message posted to Topic:" + msg+"\n");
        this.message = msg;
        this.changed = true;
        notifyObserver();
    }
}
