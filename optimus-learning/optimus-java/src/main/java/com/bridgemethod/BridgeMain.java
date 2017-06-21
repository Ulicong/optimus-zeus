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
            Method handlerMethodToInvoke = BridgeMethodResolver.findBridgedMethod(m);
            System.out.println(handlerMethodToInvoke.getName());
        }
       // BridgeInterface bridge = new BridgeImpl();
      //  bridge.method("abc");//调用实际的方法
        //bridge.method(new Object());//调用桥接方法
    }
}
