package com.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

/**
 * common-email 邮件发送
 * <p>
 * Created by li.huan
 * Create Date 2017-04-20 9:35
 */
public class SendEmail {


    public static void main(String[] args) throws Exception {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("mail.cgtz.com");
        email.setAuthentication("*****", "*****");
        email.setCharset("UTF-8");
        email.addTo("huan.li@cgtz.com");
        email.setFrom("*****", "*****");
        email.setSubject("subject中文");
        email.setMsg("msg中文");

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("f:/java.jpg");// 本地文件
        // attachment.setURL(new URL("http://xxx/a.gif"));//远程文件
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("邮件测试");
        attachment.setName("邮件测试");

        email.attach(attachment);
        email.send();
    }

}
