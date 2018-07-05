package com.qtu404.logger.domain;
//log实体类对应数据库一条操作记录
public class LogVo {
    private Integer loggerid;//自增id字段记录操作id
    private String username;//操作的对象
    private String ipadress;//操作用户的ip地址
    private String date;//操作执行的日期
    private String operation;//操作的具体类型
/**
 * getter和setter方法
 * */

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
