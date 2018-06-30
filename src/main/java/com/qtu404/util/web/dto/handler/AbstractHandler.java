package com.qtu404.util.web.dto.handler;

public abstract class AbstractHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        nextHandler = handler;
    }

    @Override
    public Object execute(Class c, String value) {
        Object obj = getValue(c, value);
        if (obj == null) {
            if (nextHandler == null) {
                return null;
            } else {
                return nextHandler.execute(c, value);
            }
        } else {
            return obj;
        }
    }

    public abstract Object getValue(Class c, String value);

    protected boolean isNum(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean isNumeric(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
}
