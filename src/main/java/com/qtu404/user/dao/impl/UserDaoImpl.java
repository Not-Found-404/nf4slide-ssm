package com.qtu404.user.dao.impl;

import com.qtu404.common.MybatisUnit;
import com.qtu404.user.dao.UserDao;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserVo> implements UserDao {
    SqlSessionFactory sqlSessionFactory = MybatisUnit.getSqlSessionFactory();

    /**
     * 通过手机号得到user
     *
     * @param phoneNum
     * @return
     */
    public UserVo fetchUserByPhone(String phoneNum) {
        String id = ".fetchByPhone";
        UserVo userVo = null;
        SqlSession sqlSession = getSqlSessionFactory().openSession();

        userVo = sqlSession.selectOne(getNamespaces() + id, phoneNum);

        sqlSession.commit();
        sqlSession.close();
        return userVo;
    }

    /**
     * 通过登录得到用户
     *
     * @param userVo
     * @return
     */
    public UserVo fetchUserByLogin(UserVo userVo) {
        String fetchUserByLogin_id = ".fetchUserByLogin";

        SqlSession sqlSession = getSqlSessionFactory().openSession();
        userVo = sqlSession.selectOne(getNamespaces() + fetchUserByLogin_id, userVo);

        sqlSession.commit();
        sqlSession.close();
        return userVo;
    }

    @Override
    protected String getNamespaces() {
        return "com.qtu404.mapper.userMapper";
    }

    @Override
    protected SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
