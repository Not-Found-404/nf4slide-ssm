package com.qtu404.publish.dao.impl;

import com.qtu404.common.MybatisUnit;
import com.qtu404.publish.dao.PublishDao;
import com.qtu404.publish.domain.Publish;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository("publishDao")
public class PublishDaoImpl extends BaseDaoImpl<Publish> implements PublishDao {

    private  String namespaces="com.qtu404.publish.dao.PublishDao";
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
