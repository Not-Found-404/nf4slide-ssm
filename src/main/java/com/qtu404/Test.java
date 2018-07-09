package com.qtu404;

import com.qtu404.answer.dao.AnswerDao;
import com.qtu404.answer.domain.Answer;
import com.qtu404.publish.domain.Publish;
import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.dao.UserDao;
import com.qtu404.user.dao.impl.UserDaoImpl;
import com.qtu404.user.domain.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnswerDao answerDao= (AnswerDao) ctx.getBean("answerDao");
        Publish publish = new Publish();
        publish.setPublishId(1);
        List<Answer> list = answerDao.findAll(publish);
        System.out.println(list);
    }
}
