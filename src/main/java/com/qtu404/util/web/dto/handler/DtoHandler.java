package com.qtu404.util.web.dto.handler;

import com.qtu404.util.web.dto.handler.impl.*;

import java.util.ArrayList;
import java.util.Date;

public class DtoHandler {
    private Handler firstHandlr;

    public DtoHandler() {
        StringHandler stringHandler = new StringHandler();
        IntegerHandler integerHandler = new IntegerHandler();
        FloatHandler floatHandler = new FloatHandler();
        DoubleHandler doubleHandler = new DoubleHandler();
        DateHandler dateHandler = new DateHandler();

        firstHandlr = stringHandler;
        stringHandler.setNextHandler(integerHandler);
        integerHandler.setNextHandler(floatHandler);
        floatHandler.setNextHandler(doubleHandler);
        doubleHandler.setNextHandler(dateHandler);
    }

    public Object getValue(Class t, String value) {
        return firstHandlr.execute(t, value);
    }

    public static void main(String[] args) {
        Object obj = new DtoHandler().getValue(Date.class, "2015-07-11");
        System.out.println(obj);
    }
}
