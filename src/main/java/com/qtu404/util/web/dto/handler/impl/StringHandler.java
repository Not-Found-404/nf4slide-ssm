package com.qtu404.util.web.dto.handler.impl;

import com.qtu404.util.web.dto.handler.AbstractHandler;

public class StringHandler extends AbstractHandler {
    @Override
    public Object getValue(Class c, String value) {
        if (String.class == c) {
            return value;
        }
        return null;
    }
}
