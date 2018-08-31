package com.qtu404.question.dao;

import com.qtu404.option.domain.Option;
import com.qtu404.question.domain.Question;
import com.qtu404.util.web.ssm.dao.BaseDao;
import java.util.List;

public interface QuestionDao extends BaseDao<Question> {
    List<Question> findAll(Integer userId);

    List<Question> findByDescription(Question question);
}
