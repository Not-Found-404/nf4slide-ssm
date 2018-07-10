package com.qtu404.question.controller;

import com.qtu404.question.dao.QuestionDao;
import com.qtu404.question.domain.Question;
import com.qtu404.question.service.QuestionService;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("question/")
@Controller("questionController")
public class QuestionController extends BaseController<Question> {

    @Resource(name="questionService")
    QuestionService questionService;

    @RequestMapping("/findAllQuestions")
    public String findAllQuestions(HttpSession session, HttpServletResponse response) {
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        List<Question> questionList = questionService.findAll(userVo.getUserId());

        writeResult(response, questionList);
        return "edit";
    }
    @RequestMapping("addNew")
    public String addNew(HttpSession session, HttpServletResponse response) {
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        Question question = Question.createDefaultQuestion(userVo.getUserId());
        question = questionService.save(question);

        writeResult(response, question);
        return "edit";
    }



    @Override
    protected BaseService<Question> getBaseService() {
        return questionService;
    }

}
