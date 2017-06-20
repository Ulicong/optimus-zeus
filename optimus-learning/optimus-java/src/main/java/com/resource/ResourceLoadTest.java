package com.resource;

/**
 * Java读取资源文件-class字节码 文件等
 * Created by li.huan
 * Create Date 2017-05-18 15:37
 */
public class ResourceLoadTest {

    /**
     * Class.getResource(String path)
     * path不以’/'开头时，默认是从此类所在的包下取资源；
     * path  以’/'开头时，则是从ClassPath根下获取；
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(ResourceLoadTest.class.getClassLoader().getResource(""));
        System.out.println(ResourceLoadTest.class.getResource(""));
        System.out.println(ResourceLoadTest.class.getResource("/"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(System.getProperty("user.dir"));
    }
}
