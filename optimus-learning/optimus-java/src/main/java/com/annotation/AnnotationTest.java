package com.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by li.huan
 * Create Date 2017-04-18 11:01
 */
public class AnnotationTest {


    private static void test(Class c) {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(FieldAnnotation.class)){
                FieldAnnotation annotation = field.getAnnotation(FieldAnnotation.class);
                System.out.print(annotation.fieldName()+"\t");
            }
        }

        Method[] methods = c.getDeclaredMethods();
        for (Method method:methods){
            if(method.isAnnotationPresent(MethodAnnotation.class)){
                MethodAnnotation annotation = method.getAnnotation(MethodAnnotation.class);
                System.out.print(annotation.value());
            }
            if(method.isAnnotationPresent(TypeAnnotation.class)){
                TypeAnnotation annotation = method.getAnnotation(TypeAnnotation.class);
                System.out.print(annotation.type().name());
            }
        }

        if(c.isAnnotationPresent(TypeAnnotation.class)) {
            TypeAnnotation annotation = (TypeAnnotation)c.getAnnotation(TypeAnnotation.class);
            System.out.print(annotation.type().name());
        }

    }


    public static void main(String[] args) {
        AnnotationTest.test(AnnotationUse.class);
    }


}
