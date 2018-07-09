package com.qtu404.user.domain;

import com.qtu404.slide.domain.SlideVo;
import java.util.List;

public class UserVo {
    private Integer userId;
    private String username;
    private String phoneNum;
    private String password;
    private String avator;
    private List<SlideVo> slideVos;

    public List<SlideVo> getSlideVos() {
        return slideVos;
    }

    public void setSlideVos(List<SlideVo> slideVos) {
        this.slideVos = slideVos;
    }

    public String getPassword() {
        return password;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
