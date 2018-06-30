package com.qtu404.util.email.message;

import com.qtu404.util.email.Parameter;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractMessage implements Message {
    protected Parameter mailParameter;

    @Override
    public void setParameter(Parameter mailParameter) {
        this.mailParameter = mailParameter;
    }

    @Override
    public abstract SimpleMailMessage getSimpleMailMessage();
}
