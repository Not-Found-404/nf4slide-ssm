package com.qtu404.answer.dao.impl;

import com.qtu404.answer.dao.AnswerDao;
import com.qtu404.answer.domain.Answer;
import com.qtu404.common.MybatisUnit;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("answerDao")
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {

    private  String namespaces="com.qtu404.answer.dao.AnswerDao";
    //注入sqlSessionFactory
    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;


    @Override
    protected String getNamespaces() {
        return namespaces;
    }

    @Override
    protected SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
