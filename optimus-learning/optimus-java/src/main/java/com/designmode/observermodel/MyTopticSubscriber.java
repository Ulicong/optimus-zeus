package com.designmode.observermodel;

/**
 * 观察者实现类
 * Created by li.huan
 * Create Date 2017-04-25 17:16
 */
public class MyTopticSubscriber implements Observer {

    private String name;
    private Subject topic;


    public MyTopticSubscriber(String name) {
        this.name = name;
    }

    public void update() {
        String msg = (String) topic.getUpdate(this);

        if (msg == null) {
            System.out.print(name + ":: No New message.\n");
        } else {
            System.out.print(name + ":: Consuming message::" + msg+"\n");
        }


    }

    public void setSubject(Subject obj) {
        this.topic = obj;
    }
}
