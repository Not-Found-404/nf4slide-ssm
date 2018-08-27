package com.qtu404.answer.domain;

import com.qtu404.publish.domain.Publish;

import java.util.Date;

public class Answer {
    private Integer answerId;
    private Integer publishId;
    private String ipAddress;
    private Date time;
    private Publish publish;
    // 20180823新添加的字段,答题者的信息
    private String respondentInfo;

    public Publish getPublish() {
        return publish;
    }

    public void setPublish(Publish publish) {
        this.publish = publish;
    }

    public Answer() {
    }

    public String getRespondentInfo() {
        return respondentInfo;
    }

    public void setRespondentInfo(String respondentInfo) {
        this.respondentInfo = respondentInfo;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", publishId=" + publishId +
                ", ipAddress='" + ipAddress + '\'' +
                ", time=" + time +
                '}';
    }
}
