package com.qtu404.answer.dao;

import com.qtu404.answer.domain.Answer;
import com.qtu404.publish.domain.Publish;
import com.qtu404.util.web.ssm.dao.BaseDao;
import java.util.List;

public interface AnswerDao extends BaseDao<Answer> {

   public List<Answer> findAll(Publish publish);
}
