package com.qtu404.statistics.dao.impl;

import com.qtu404.statistics.dao.ViewStatisticsDao;
import com.qtu404.statistics.domain.ViewStatistics;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ViewStatisticsDaoImpl extends BaseDaoImpl<ViewStatistics> implements ViewStatisticsDao {
    private final SqlSessionFactory sqlSessionFactory;

    @Autowired
    public ViewStatisticsDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    protected String getNamespaces() {
        return "com.qtu404.answer.dao.ViewStatisticsDao";
    }

    public List<ViewStatistics> listByCondition(Map<String, Object> condition) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        List<ViewStatistics> rest = sqlSession.selectList(this.sqlId("listCriteria"), condition);
        sqlSession.commit();
        sqlSession.close();
        return rest;
    }

    @Override
    protected SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    private String sqlId(String id) {
        return this.getNamespaces() + "." + id;
    }
}
