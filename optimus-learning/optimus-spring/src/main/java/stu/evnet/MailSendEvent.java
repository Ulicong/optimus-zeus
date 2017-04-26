package stu.evnet;


import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * Created by li.huan
 * Create Date 2017-04-26 9:07
 */
public class MailSendEvent extends ApplicationContextEvent {

    private String to;

    public MailSendEvent(ApplicationContext source, String to) {
        super(source);
        this.to = to;
    }


    public String getTo() {
        return this.to;
    }

}
