package com.qtu404.publish.service.impl;

import com.qtu404.publish.dao.PublishDao;
import com.qtu404.publish.domain.Publish;
import com.qtu404.publish.service.PublishService;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("publishService")
public class PublishServiceImpl extends BaseServiceImpl<Publish> implements PublishService {

    @Resource(name="publishDao")
    PublishDao publishDao;

    @Override
    protected BaseDao<Publish> getBaseDao() {
        return publishDao;
    }


    @Override
    public List<Publish> findPublishByQuestionId(Integer questionId) {
        return publishDao.findPublishByQuestionId(questionId);
    }
}
