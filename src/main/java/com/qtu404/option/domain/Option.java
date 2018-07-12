package com.qtu404.option.domain;

public class Option {
    private Integer optionId;
    private Integer questionId;
    private String content;

    public Option() {
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOptionId() {

        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionId=" + optionId +
                ", questionId=" + questionId +
                ", content='" + content + '\'' +
                '}';
    }
}
