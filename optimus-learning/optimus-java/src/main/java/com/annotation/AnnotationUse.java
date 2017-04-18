package com.annotation;

/**
 * 注解使用类
 * Created by li.huan
 * Create Date 2017-04-18 10:31
 */
@TypeAnnotation
public class AnnotationUse {

    @FieldAnnotation(fieldName = "annotation-测试")
    private String name;

    public AnnotationUse() {
        super();
    }


    @TypeAnnotation
    @MethodAnnotation(value = "display name")
    public void disPlayName() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
