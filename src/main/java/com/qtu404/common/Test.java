package com.qtu404.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.answer.dao.AnswerDao;
import com.qtu404.answer.domain.Answer;
import com.qtu404.option.domain.Option;
import com.qtu404.option.service.OptionService;
import com.qtu404.publish.domain.Publish;
import com.qtu404.question.domain.Question;
import com.qtu404.question.service.QuestionService;
import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.slide.service.SlideService;
import com.qtu404.user.dao.UserDao;
import com.qtu404.user.dao.impl.UserDaoImpl;
import com.qtu404.user.domain.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionService questionService = (QuestionService) ctx.getBean("questionService");


        Question question = new Question();
        question.setUserId(100025);
        question.setDescription("");
        List<Question> questions = questionService.findByDescription(question);
        test.loggerTest();
    }


    public void loggerTest() {

    }
}
