package com.qtu404.util.web.dto.handler.impl;

import com.qtu404.util.web.dto.handler.AbstractHandler;

public class IntegerHandler extends AbstractHandler {
    @Override
    public Object getValue(Class c, String value) {
        if (!isNum(value)) {
            return null;
        } else if (c == Integer.class || c == int.class) {
            return Integer.parseInt(value);
        }
        return null;
    }
}
