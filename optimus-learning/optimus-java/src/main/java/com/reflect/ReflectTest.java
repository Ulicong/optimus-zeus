package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by li.huan.
 * Created on 2017/4/22
 * <p>
 * public Method[] getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法<br>
 * public Method[] getDeclaredMethods()对象表示的类或接口声明的所有方法，<br>
 * 包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。
 */
@SuppressWarnings(value = {"unchecked"})
public class ReflectTest {


    private static Car iniByDefaultConts() throws Throwable {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.reflect.Car");
        Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) cons.newInstance();

        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "本田思域");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "暗金蓝");
        Method setMaxSpeed = clazz.getDeclaredMethod("setMaxSpeed", int.class);
        setMaxSpeed.setAccessible(true);//调用private protected方法时需手动设置true
        setMaxSpeed.invoke(car, 200);
        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = ReflectTest.iniByDefaultConts();
        car.disPlay();
    }
}
