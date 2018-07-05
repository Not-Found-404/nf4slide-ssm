package com.qtu404.option.dao.impl;

import com.qtu404.common.MybatisUnit;
import com.qtu404.option.dao.OptionDao;
import com.qtu404.option.domain.Option;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("optionDao")
public class OptionDaoImpl extends BaseDaoImpl<Option> implements OptionDao {

    private  String namespaces="com.qtu404.option.dao.OptionDao";
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
