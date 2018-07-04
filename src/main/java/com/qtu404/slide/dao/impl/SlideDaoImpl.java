package com.qtu404.slide.dao.impl;

import com.qtu404.common.MybatisUnit;
import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("slideDao")
public class SlideDaoImpl extends BaseDaoImpl<SlideVo> implements SlideDao {
    private String namespaces = "com.qtu404.mapper.slideMapper";

    @Resource(name="sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<SlideVo> findAllSlideByUserId(Integer userId) {
        List<SlideVo> slideVos = null;
        String findAllByUserId_id = ".findAllByUserId";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        slideVos = sqlSession.selectList(namespaces + findAllByUserId_id, userId);
        return slideVos;
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