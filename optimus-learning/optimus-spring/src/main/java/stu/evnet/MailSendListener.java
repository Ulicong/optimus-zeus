package stu.evnet;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by li.huan
 * Create Date 2017-04-26 9:21
 */
@Component
public class MailSendListener implements ApplicationListener<MailSendEvent> {

    public void onApplicationEvent(MailSendEvent event) {
        System.out.print("MailSendListener:向" + event.getTo() + "发送一份邮件.\n");
    }
}
