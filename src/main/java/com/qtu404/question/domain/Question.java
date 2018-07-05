package com.qtu404.question.domain;

public class Question {
    private Integer questionId;
    private Integer userId;
    private String description;
    private Integer originalAnswer;

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
