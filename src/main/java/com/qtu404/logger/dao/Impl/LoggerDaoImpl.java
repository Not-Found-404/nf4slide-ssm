package com.qtu404.logger.dao.Impl;

import com.qtu404.logger.dao.LoggerDao;
import com.qtu404.logger.domain.LogVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
@Repository("loggerDao")
public class LoggerDaoImpl implements LoggerDao {
    //注入sqlSessionFactory对象
    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;
    /**将一条操作记录插入到数据库
     * @param log
     * @return
     * */
    public void record(LogVo log){
        String statement ="com.qtu404.mapper.loggerMapper.save";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(statement,log);
        sqlSession.commit();
        sqlSession.close();
    }

}
