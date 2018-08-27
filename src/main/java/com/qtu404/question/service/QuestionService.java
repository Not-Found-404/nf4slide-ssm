package com.qtu404.question.service;

import com.qtu404.option.domain.Option;
import com.qtu404.question.domain.Question;
import com.qtu404.util.web.ssm.service.BaseService;

import java.util.List;

public interface QuestionService extends BaseService<Question>  {
    List<Question> findAll(Integer userId);
}
