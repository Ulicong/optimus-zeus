package com.reflect;

/**
 * java 类加载
 * Created by li.huan.
 * Created on 2017/4/22
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.print("current loader:" + loader + "\n");
        System.out.print("parent loader:" + loader.getParent() + "\n");
        System.out.print("grandParent loader:" + loader.getParent().getParent() + "\n");
    }
}
