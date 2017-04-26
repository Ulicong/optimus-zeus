package stu.evnet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by li.huan
 * Create Date 2017-04-26 9:26
 */
@Component
public class MailSender implements ApplicationContextAware {

    private ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void sendMail(String to) {
        System.out.print("模拟发邮件事件.");
        MailSendEvent mailSendEvent = new MailSendEvent(context, to);
        context.publishEvent(mailSendEvent);
    }
}
