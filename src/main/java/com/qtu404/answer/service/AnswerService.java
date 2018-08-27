package com.qtu404.answer.service;

import com.qtu404.answer.domain.Answer;
import com.qtu404.publish.domain.Publish;
import com.qtu404.util.web.ssm.service.BaseService;

import java.util.List;

public interface AnswerService extends BaseService<Answer> {
    List<Answer> findPublishByQuestionId(Publish publish);
}
