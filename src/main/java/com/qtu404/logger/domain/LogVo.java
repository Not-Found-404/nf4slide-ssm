package com.qtu404.logger.domain;

public class LogVo {
    private Integer loggerid;
    private String username;
    private String ipadress;
    private String date;
    private String operation;

    public Integer getLoggerid() {
        return loggerid;
    }

    public void setLoggerid(Integer loggerid) {
        this.loggerid = loggerid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpadress() {
        return ipadress;
    }

    public void setIpadress(String ipadress) {
        this.ipadress = ipadress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
