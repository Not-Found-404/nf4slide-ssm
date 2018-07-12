package com.qtu404.answer.controller;

import com.qtu404.answer.domain.Answer;
import com.qtu404.answer.service.AnswerService;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("answer/")
@Controller
public class AnswerController extends BaseController<Answer> {

    @Resource(name="answerService")
    AnswerService answerService;

    @Override
    protected BaseService<Answer> getBaseService() {
        return answerService;
    }
}
