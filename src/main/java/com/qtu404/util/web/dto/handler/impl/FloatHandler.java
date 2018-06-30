package com.qtu404.util.web.dto.handler.impl;

import com.qtu404.util.web.dto.handler.AbstractHandler;

public class FloatHandler extends AbstractHandler {
    @Override
    public Object getValue(Class c, String value) {
        Float obj = null;
        if (!isNumeric(value)) {
            return obj;
        } else if (c == float.class || c == Float.class) {
            obj = Float.parseFloat(value);
        }
        return obj;
    }
}
