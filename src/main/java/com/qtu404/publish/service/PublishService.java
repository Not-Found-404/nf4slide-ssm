package com.qtu404.publish.service;

import com.qtu404.publish.domain.Publish;
import com.qtu404.util.web.ssm.service.BaseService;

import java.util.List;

public  interface PublishService extends BaseService<Publish> {
    List<Publish> findPublishByQuestionId(Integer questionId);
}
