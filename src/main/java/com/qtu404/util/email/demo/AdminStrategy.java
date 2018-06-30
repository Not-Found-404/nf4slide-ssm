package com.qtu404.util.email.demo;

import com.qtu404.util.email.strategy.AbstractStrategy;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class AdminStrategy extends AbstractStrategy {
    public AdminStrategy() {
        super("account@qtu404.com");
    }

    @Override
    public MailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qtu404.com");//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(25);//默认端口，标准的SMTP端口
        mailSender.setPassword("account.qtu404");//密码
        mailSender.setUsername(senderAddress);//用户名
        return mailSender;
    }
}
