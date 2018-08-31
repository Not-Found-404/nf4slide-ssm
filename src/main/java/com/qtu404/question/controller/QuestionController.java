package com.qtu404.question.controller;

import com.alibaba.fastjson.JSON;
import com.qtu404.option.domain.Option;
import com.qtu404.option.service.OptionService;
import com.qtu404.question.domain.Question;
import com.qtu404.question.service.QuestionService;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("question/")
@Controller
public class QuestionController extends BaseController<Question> {

    @Resource(name = "questionService")
    QuestionService questionService;

    @Resource(name = "optionService")
    OptionService optionService;

    @RequestMapping("/findAllQuestions")
    public String findAllQuestions(HttpSession session, HttpServletResponse response) {
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        List<Question> questionList = questionService.findAll(userVo.getUserId());

        writeResult(response, questionList);
        return "edit";
    }

    @RequestMapping(value = "addNew", method = RequestMethod.GET)
    public String addNew(HttpSession session, HttpServletResponse response) {
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        Question question = Question.createDefaultQuestion(userVo.getUserId());
        question = questionService.save(question);

        writeResult(response, question);
        return "edit";
    }

    @RequestMapping(value = "/fetchQuestionById", method = RequestMethod.POST)
    public void fetchQuestionById(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        String questionId = request.getParameter("questionId");
        Question question = questionService.fetchById(Integer.parseInt(questionId));
        writeResult(response, question);
    }

    @RequestMapping("/modifyQeustion")
    public void modifySlideInfo(@RequestBody String body, HttpServletResponse response, HttpServletRequest request) {
        Question dto = JSON.parseObject(body, Question.class);
        List<Option> optionList = dto.getOptionList();
        int rst1 = optionService.modify(optionList);

        HttpSession session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        dto.setUserId(userVo.getUserId());
        int rst = questionService.modify(dto);
        Result result = new Result();
        if (rst != 1) {
            result.setResult("modify Success");
            result.setCode(200);
        } else {
            result.setCode(500);
            result.setResult("modify fail");
        }
        writeResult(response, result);
    }

    @RequestMapping("deleteQuestion")
    public void deleteQuestion(@RequestBody String body, HttpServletResponse response, HttpServletRequest request) {
        Question dto = new Question();
        dto.setQuestionId(Integer.parseInt(body));
        int rst = questionService.delete(dto);
        Result result = new Result();
        if (rst == 1) {
            result.setCode(200);
            result.setResult("delete Success");
        } else {
            result.setResult("delete fail");
            result.setCode(500);
        }
        writeResult(response, result);
    }

    @RequestMapping("findByDescription")
    public void findByDescription(@RequestBody String body, HttpServletResponse response, HttpServletRequest request) {
        Question dto = new Question();
        dto.setUserId(((UserVo) request.getSession().getAttribute("loginUser")).getUserId());
        dto.setDescription(body);
        List<Question> questions = questionService.findByDescription(dto);
        writeResult(response, questions);
    }

    @Override
    protected BaseService<Question> getBaseService() {
        return questionService;
    }

}
