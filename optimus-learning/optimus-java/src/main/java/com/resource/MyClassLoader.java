package com.resource;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 你在URLClassLoader类里定义了多个带参数的构造方法，那么URLClassLoader这个类就没有无参数的构造方法了。所以只能由子类实例化它。
 * <p>
 * 子类在继承父类时，如果没有相同的带参构造方法，那么他就需要在其构造方法中明确的通过super()调用父类的带参构造方法，否则构造不出父类，从而也构造不出他自己了。
 * <p>
 * 你如果在父类中写个不带参数的构造方法，就可以不用实现父类的带参构造方法了
 * Created by li.huan
 * Create Date 2017-05-19 11:05
 */
public class MyClassLoader extends URLClassLoader {

    /**
     * 上面注释解释了该类为什么要有一个有参的构造函数
     *
     * @param urls
     */
    public MyClassLoader(URL[] urls) {
        super(urls);
    }


}
