package com.qtu404.util.web.dto.handler;

interface Handler {
    void setNextHandler(Handler handler);

    Object execute(Class c, String value);
}