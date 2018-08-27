package com.qtu404.answer.service.impl;

import com.qtu404.answer.dao.AnswerDao;
import com.qtu404.answer.domain.Answer;
import com.qtu404.answer.service.AnswerService;
import com.qtu404.publish.domain.Publish;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("answerService")
public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements AnswerService {

    @Resource(name="answerDao")
    AnswerDao answerDao;

    @Override
    protected BaseDao<Answer> getBaseDao() {
        return answerDao;
    }

    @Override
    public List<Answer> findPublishByQuestionId(Publish publish) {
        return answerDao.findAll(publish);
    }
}
