package stu.resources;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Spring资源文件加载机制
 * <p>
 * Created by li.huan.
 * Created on 2017/4/22
 */
public class SpringResourcesTest {


    /**
     * 扫描当前工程以及所有spring-jar包中后缀是.xsd的文件
     * spring资源加载支持Ant风格<br>
     * ？:匹配文件中一个字符
     * *:匹配文件中任意一个字符
     * **:匹配多成路径
     */
    public static void main(String[] args) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:org/springframework/**/*.xsd");
        for (Resource resource : resources) {
            System.out.print(resource.getDescription() + "\n");
        }
    }

}
