package com.qtu404.question.service.impl;

import com.qtu404.option.dao.OptionDao;
import com.qtu404.option.domain.Option;
import com.qtu404.question.dao.QuestionDao;
import com.qtu404.question.domain.Question;
import com.qtu404.question.service.QuestionService;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {

    @Resource(name = "questionDao")
    QuestionDao questionDao;
    @Resource(name = "optionDao")
    OptionDao optionDao;

    @Override
    protected BaseDao<Question> getBaseDao() {
        return questionDao;
    }

    @Override
    public Question save(Question question) {
        questionDao.save(question);
        Integer questionId = question.getQuestionId();
        for (Option item : question.getOptionList()) {
            item.setQuestionId(questionId);
            optionDao.save(item);
        }
        return question;
    }


    @Override
    public List<Question> findAll(Integer userId) {
        return questionDao.findAll(userId);
    }

    @Override
    public List<Question> findByDescription(Question dto) {
        dto.setDescription("%" + dto.getDescription() + "%");
        return questionDao.findByDescription(dto);
    }

}
