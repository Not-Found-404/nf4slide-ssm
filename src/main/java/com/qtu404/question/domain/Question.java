package com.qtu404.question.domain;

import com.qtu404.publish.domain.Publish;

import java.util.List;

public class Question {
    private Integer questionId;
    private Integer userId;
    private String description;
    private Integer originalAnswer;
    private List<Publish> publishList;

    public List<Publish> getPublishList() {
        return publishList;
    }

    public void setPublishList(List<Publish> publishList) {
        this.publishList = publishList;
    }

    public Question() {
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOriginalAnswer() {
        return originalAnswer;
    }

    public void setOriginalAnswer(Integer originalAnswer) {
        this.originalAnswer = originalAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", originalAnswer=" + originalAnswer +
                '}';
    }
}
