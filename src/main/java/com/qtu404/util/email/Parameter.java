package com.qtu404.util.email;

import java.util.HashMap;

public class Parameter {
    private HashMap<String, Object> parameter;

    public Parameter() {
        parameter = new HashMap<>(5);
    }

    public void setAttribute(String key, Object value) {
        parameter.put(key, value);
    }

    public Object getAttribute(String key) {
        return parameter.get(key);
    }


    public void clear() {
        parameter.clear();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Parameter myParameter = new Parameter();
        myParameter.parameter = (HashMap<String, Object>) this.parameter.clone();
        return myParameter;
    }
}
