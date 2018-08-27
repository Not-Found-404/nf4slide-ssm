package com.qtu404.question.domain;

import com.qtu404.option.domain.Option;
import com.qtu404.publish.domain.Publish;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private Integer questionId;
    private Integer userId;
    private String description;
    private Integer originalAnswer;
    private List<Option> optionList;
    private List<Publish> publishList;

    public List<Publish> getPublishList() {
        return publishList;
    }

    public void setPublishList(List<Publish> publishList) {
        this.publishList = publishList;
    }

    public Question() {
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
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

    public static void main(String[] args) {
        Question question = Question.createDefaultQuestion(001);
        System.out.println();
    }
    public static Question createDefaultQuestion(Integer userId){
        Question question = new Question();
        List<Option> optionList = new ArrayList<Option>(4);
        for (int i = 0;i < 4; i++){
            Option option = new Option();
            optionList.add(option);
            option.setContent("选项" + i);
        }
        question.setUserId(userId);
        question.setDescription("新建问题");
        question.setOptionList(optionList);
        question.setOriginalAnswer(0);
        
        return  question;
    }
}
