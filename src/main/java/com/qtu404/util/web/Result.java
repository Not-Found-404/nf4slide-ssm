package com.qtu404.util.web;

public class Result {
    private String result;
    private Integer code;

    public Result() {
        result = "fail";
        code = 500;
    }

    public String getResult() {
        return result;
    }

    public Result setResult(String result) {
        this.result = result;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }
}
