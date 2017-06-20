package com.resource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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


    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    protected Class<?> load(String name, boolean resolve) throws ClassNotFoundException {
        //若类已经被加载、则重新加载一次
        Class<?> loadedClass = super.findLoadedClass(name);
        if (loadedClass != null) {
            return reload(name, resolve);
        }

        //否则首次加载它
        Class<?> aClass = super.findClass(name);
        if (resolve) {
            super.resolveClass(aClass);
        }
        return aClass;
    }


    public Class<?> reload(String name, boolean resolve) throws ClassNotFoundException {
        return new MyClassLoader(super.getURLs(), super.getParent())
                .load(name, resolve);
    }

    public Class<?> load(String name) throws ClassNotFoundException {
        return load(name, false);
    }
}

/**
 * 重复加载Class A
 */
class TestClassLoad {
    public static void main(String[] args) {
        A a = new A();  // 加载类A
        B b = new B();  // 加载类B
        a.setB(b);  // A引用了B，把b对象拷贝到A.b
        System.out.printf("A classLoader is %s\n", a.getClass().getClassLoader());
        System.out.printf("B classLoader is %s\n", b.getClass().getClassLoader());
        System.out.printf("A.b classLoader is %s\n", a.getB().getClass().getClassLoader());

        try {
            URL[] urls = new URL[]{Thread.currentThread().getContextClassLoader().getResource("")};
            MyClassLoader c1 = new MyClassLoader(urls, a.getClass().getClassLoader());
            Class clazz = c1.load("com.resource.A");  // 用hot swap重新加载类A
            Object aInstance = clazz.newInstance();  // 创建A类对象
            Method method1 = clazz.getMethod("setB", B.class);  // 获取setB(B b)方法
            method1.invoke(aInstance, b);    // 调用setB(b)方法，重新把b对象拷贝到A.b
            Method method2 = clazz.getMethod("getB");  // 获取getB()方法
            Object bInstance = method2.invoke(aInstance);  // 调用getB()方法
            System.out.printf("Reloaded A.b classLoader is %s\n", bInstance.getClass().getClassLoader());
        } catch (ClassNotFoundException |
                InstantiationException | IllegalAccessException |
                NoSuchMethodException | SecurityException |
                IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}