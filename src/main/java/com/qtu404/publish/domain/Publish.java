package com.qtu404.publish.domain;

import java.util.Date;

public class Publish {
    private Integer publishId;
    private Integer questionId;
    private Date time;

    public Publish() {
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Publish{" +
                "publishId=" + publishId +
                ", questionId=" + questionId +
                ", time=" + time +
                '}';
    }
}
