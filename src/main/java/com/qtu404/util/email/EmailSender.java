package com.qtu404.util.email;


import com.qtu404.util.email.message.Message;
import com.qtu404.util.email.strategy.Strategy;

public class EmailSender {
    private Message message = null;
    private Strategy emailStrategy = null;

    public void setEmailStrategy(Strategy emailStrategy) {
        this.emailStrategy = emailStrategy;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public EmailSender(Message message, Strategy emailStrategy) {
        this.message = message;
        this.emailStrategy = emailStrategy;
    }

    public void SendEmail(Parameter emailParameter) {
        String sender = emailStrategy.getSenderAddress();
        emailParameter.setAttribute("sender", sender);
        message.setParameter(emailParameter);
        emailStrategy.getMailSender().send(message.getSimpleMailMessage());
    }
}
