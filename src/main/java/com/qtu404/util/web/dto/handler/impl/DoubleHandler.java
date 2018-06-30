package com.qtu404.util.web.dto.handler.impl;

import com.qtu404.util.web.dto.handler.AbstractHandler;

public class DoubleHandler extends AbstractHandler {
    @Override
    public Object getValue(Class c, String value) {
        Double obj = null;
        if (!isNumeric(value)) {
            obj = null;
        } else if (c == Double.class || c == double.class) {
            obj = Double.parseDouble(value);
        }
        return obj;
    }
}
