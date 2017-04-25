package com.designmode.observermodel;

/**
 * 观察者模式
 * Subject(被观察者)包含了一些需要在其状态改变时通知的观察者。因此，他应该提供给观察者可以register(注册)自己和unregister(注销)自己的方法。
 * 当Subject(被观察者)发生变化的时候，也需要包含一个方法来通知所有观察者。当通知观察者的时候，可以推送更新内容，或者提供另外一个方法来获得更新内容
 * MVC模型-视图-控制框架也使用观察者模式，把模型当做被观察者，视图视为观察者。视图能够注册自己到模型上来获得模型的改变。
 * <p>
 * Created by li.huan
 * Create Date 2017-04-25 16:49
 */
public class ObserverModelTest {

    public static void main(String[] args){
        MyTopic myTopic = new MyTopic();

        MyTopticSubscriber observer1 = new MyTopticSubscriber("observer1");
        MyTopticSubscriber observer2 = new MyTopticSubscriber("observer2");
        MyTopticSubscriber observer3 = new MyTopticSubscriber("observer3");

        myTopic.register(observer1);
        myTopic.register(observer2);
        myTopic.register(observer3);

        observer1.setSubject(myTopic);
        observer2.setSubject(myTopic);
        observer3.setSubject(myTopic);


        observer1.update();
        myTopic.postMessage("new message");

    }
}
