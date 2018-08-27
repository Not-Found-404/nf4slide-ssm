package com.qtu404.answer.controller;

import com.alibaba.fastjson.JSON;
import com.qtu404.answer.domain.Answer;
import com.qtu404.answer.service.AnswerService;
import com.qtu404.publish.domain.Publish;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("answer/")
@Controller
public class AnswerController extends BaseController<Answer> {

    @Resource(name="answerService")
    AnswerService answerService;

    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    public void saveAnswer(HttpServletRequest request, HttpServletResponse response) {

        Answer answer = getDtoObject(request);
        answer.setTime(new Date());
        answer = answerService.save(answer);
        writeResult(response,answer);

    }
    @RequestMapping(value = "/fetchAnswerByPublishId", method = RequestMethod.POST)
    public void fetchAnswerByPublishId(@RequestBody String publishBody,HttpSession session, HttpServletResponse response, HttpServletRequest request) {
       Publish publish = JSON.parseObject(publishBody,Publish.class);
        List<Answer> answerList = answerService.findPublishByQuestionId(publish);
        writeResult(response, answerList);
    }

    @Override
    protected BaseService<Answer> getBaseService() {
        return answerService;
    }
}
