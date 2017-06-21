package com.bridgemethod;

import org.springframework.core.BridgeMethodResolver;

import java.lang.reflect.Method;

/**
 * Created by li.huan.
 * Created on 2017/6/21
 */
public class BridgeMain {




    public static void main(String[] args) {
        Method[] methods = BridgeImpl.class.getMethods();
        for (Method m : methods){
            //通过spring-BridgeMethodResolver获取正确的桥接方法 过判断方法名、参数的个数以及泛型类型参数来获取
            Method handlerMethodToInvoke = BridgeMethodResolver.findBridgedMethod(m);
            System.out.println(handlerMethodToInvoke.getName());
        }
       // BridgeInterface bridge = new BridgeImpl();
      //  bridge.method("abc");//调用实际的方法
        //bridge.method(new Object());//调用桥接方法
    }
}
