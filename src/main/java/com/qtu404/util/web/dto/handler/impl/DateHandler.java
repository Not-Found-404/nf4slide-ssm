package com.qtu404.util.web.dto.handler.impl;

import com.qtu404.util.web.dto.handler.AbstractHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler extends AbstractHandler {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object getValue(Class c, String value) {
        Date date = null;
        if (c == Date.class) {
            try {
                date = df.parse(value);
            } catch (ParseException e) {
                return null;
            }
            return date;
        }
        return null;
    }
}
