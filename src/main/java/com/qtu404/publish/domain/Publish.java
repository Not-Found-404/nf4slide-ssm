package com.qtu404.publish.domain;

import com.qtu404.answer.domain.Answer;
import com.qtu404.question.domain.Question;
import java.util.Date;
import java.util.List;

public class Publish {
    private Integer publishId;
    private Integer questionId;
    private Date time;
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    private List<Answer> answerList;

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
