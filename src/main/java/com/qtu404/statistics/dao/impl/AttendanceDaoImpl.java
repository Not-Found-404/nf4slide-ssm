package com.qtu404.statistics.dao.impl;

import com.qtu404.statistics.dao.AttendanceDao;
import com.qtu404.statistics.domain.Attendance;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class AttendanceDaoImpl extends BaseDaoImpl<Attendance> implements AttendanceDao {
    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    @Override
    protected String getNamespaces() {
        return "com.qtu404.answer.dao.AttendanceDao";
    }

    @Override
    protected SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    @Override
    public List<Attendance> listByCondition(Map<String, Object> condition) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        List<Attendance> rest = sqlSession.selectList(this.sqlId("listCriteria"), condition);
        sqlSession.commit();
        sqlSession.close();
        return rest;
    }

    private String sqlId(String id) {
        return this.getNamespaces() + "." + id;
    }
}
