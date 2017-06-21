package com.bridgemethod;

/**
 * doc_url:http://blog.csdn.net/zghwaicsdn/article/details/50717334
 * java 中桥接方法
 * 什么时候会生成桥接方法
 * 就是说一个子类在继承（或实现）一个父类（或接口）的泛型方法时，在子类中明确指定了泛型类型，
 * 那么在编译时编译器会自动生成桥接方法（当然还有其他情况会生成桥接方法，这里只是列举了其中一种情况）
 * <p>
 * 为什么要生成桥接方法
 * 因为泛型是在1.5引入泛型为了向前兼容，所以会在编译时去掉泛型（泛型擦除），但是我们还是可以通过反射API来获取泛型的信息，
 * 在编译时可以通过泛型来保证类型的正确性，而不必等到运行时才发现类型不正确。由于java泛型的擦除特性，如果不生成桥接方法，那么与1.5之前的字节码就不兼容了。如前面的SuperClass中的方法
 * Created by li.huan.
 * Created on 2017/6/21
 */
public class BridgeImpl implements BridgeInterface<String> {

    public String method(String param) {
        return param;
    }
}
