package stu.evnet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by li.huan
 * Create Date 2017-04-26 9:35
 */
public class MailEventTest {


    public static void main(String[] args) {
        String[] xmlPath = new String[]{"classpath:config/springMVC-servlet.xml",
                "classpath:config/applicationContext.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        MailSender mailSender = (MailSender) context.getBean("mailSender");
        mailSender.sendMail("xxx@qq.com");
    }
}
