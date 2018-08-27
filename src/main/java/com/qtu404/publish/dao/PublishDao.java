package com.qtu404.publish.dao;

import com.qtu404.publish.domain.Publish;
import com.qtu404.util.web.ssm.dao.BaseDao;

import java.util.List;

public interface PublishDao extends BaseDao<Publish> {
    List<Publish> findPublishByQuestionId(Integer questionId);
}
