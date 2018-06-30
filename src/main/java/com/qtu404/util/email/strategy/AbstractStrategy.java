package com.qtu404.util.email.strategy;

import org.springframework.mail.MailSender;

public abstract class AbstractStrategy implements Strategy {
    protected String senderAddress = null;

    protected AbstractStrategy(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    @Override
    public String getSenderAddress() {
        return senderAddress;
    }

    @Override
    public abstract MailSender getMailSender();
}
