package com.qtu404.question.dao.impl;

import com.qtu404.question.dao.QuestionDao;
import com.qtu404.question.domain.Question;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("questionDao")
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao {

    private String namespaces = "com.qtu404.question.dao.QuestionDao";
    //注入sqlSessionFactory
    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public List<Question> findAll(Integer userId) {
        String id = ".findAll";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Question> questionList = null;
        questionList = sqlSession.selectList(getNamespaces() + id, userId);
        sqlSession.commit();
        sqlSession.close();
        return questionList;
    }

    @Override
    protected String getNamespaces() {
        return namespaces;
    }

    @Override
    protected SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
