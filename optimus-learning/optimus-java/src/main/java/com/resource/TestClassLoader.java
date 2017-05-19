package com.resource;

import java.util.Map;

/**
 * Created by li.huan
 * Create Date 2017-05-18 14:43
 */
public class TestClassLoader {


    public static void main(String[] args) throws Exception {
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resourcePatternResolver.getResources("classpath*:stu/**/*.class");
//        for (Resource sr: resources){
//            System.out.println(sr.getFilename());
//        }
//        URL resource = TestClassLoader.class.getClassLoader().getResource("");
//        System.out.println(resource);


//        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
//        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("");
//        for(BeanDefinition bean: beanDefinitions){
//            System.out.println(bean.getClass());
//        }

//
        for (Map.Entry<Object,Object> entry: System.getProperties().entrySet()){
            System.out.println(entry.getKey()+"\t"+ entry.getValue());
        }
//        System.out.println(TestClassLoader.class.getClassLoader());
//        System.out.print(Thread.currentThread().getContextClassLoader());
    }
}
