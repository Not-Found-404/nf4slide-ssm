package com.qtu404;

import com.qtu404.answer.dao.AnswerDao;
import com.qtu404.answer.domain.Answer;
import com.qtu404.option.domain.Option;
import com.qtu404.option.service.OptionService;
import com.qtu404.publish.domain.Publish;
import com.qtu404.question.domain.Question;
import com.qtu404.question.service.QuestionService;
import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.domain.SlideVo;
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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionService questionService = (QuestionService) ctx.getBean("questionService");
//        AnswerDao answerDao= (AnswerDao) ctx.getBean("answerDao");
//        Publish publish = new Publish();
//        publish.setPublishId(1);
//        List<Answer> list = answerDao.findAll(publish);
//        System.out.println(list);
//        Question question = Question.createDefaultQuestion(100025);
//        Question dto = questionService.fetchById(308);
//        dto.setDescription("1111111111");
//        questionService.modify(dto);
//        OptionService optionService = (OptionService) ctx.getBean("optionService");
//        Option option = optionService.fetchById(677);
//        option.setContent("11125");
//        optionService.modify(option);
//        System.out.println();
    }
}
