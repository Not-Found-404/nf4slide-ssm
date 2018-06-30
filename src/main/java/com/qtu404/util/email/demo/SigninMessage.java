package com.qtu404.util.email.demo;

import com.qtu404.util.email.message.AbstractMessage;
import org.springframework.mail.SimpleMailMessage;

public class SigninMessage extends AbstractMessage {
    @Override
    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom((String) mailParameter.getAttribute("sender"));//发件人
        message.setTo((String) mailParameter.getAttribute("receiver"));//收件人
        message.setSubject("注册验证");//主题
        message.setText("欢迎注册NF4随想,您的注册验证码为:" + mailParameter.getAttribute("verification"));//正文
        return message;
    }
}
