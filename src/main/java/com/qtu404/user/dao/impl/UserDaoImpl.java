package com.qtu404.user.dao.impl;

import com.qtu404.common.MybatisUnit;
import com.qtu404.user.dao.UserDao;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.dao.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserVo> implements UserDao {
    private String namespaces = "com.qtu404.mapper.userMapper";
//注入sqlSessionFactory
@Resource(name = "sqlSessionFactory")
SqlSessionFactory sqlSessionFactory;

    /**
     * 通过手机号得到user
     *
     * @param phoneNum
     * @return UserVo
     */
    public UserVo fetchUserByPhone(String phoneNum) {
        String id = ".fetchByPhone";
        UserVo userVo = null;
        SqlSession sqlSession = sqlSessionFactory.openSession();

        userVo = (UserVo) sqlSession.selectOne(getNamespaces() + id, phoneNum);

        sqlSession.commit();
        sqlSession.close();
        return userVo;
    }

    /**
     * 通过登录得到用户
     *
     * @param userVo
     * @return UserVo
     */
    public UserVo fetchUserByLogin(UserVo userVo) {
        String fetchUserByLogin_id = ".fetchUserByLogin";

        SqlSession sqlSession = sqlSessionFactory.openSession();
        userVo = (UserVo) sqlSession.selectOne(getNamespaces() + fetchUserByLogin_id, userVo);

        sqlSession.commit();
        sqlSession.close();
        return userVo;
    }
    /**
     * 获取命名空间
     *
     * @param
     * @return namespaces(String)
     */
    @Override
    protected String getNamespaces() {
        return namespaces;
    }

    /**
     * 获取sqlSessionFactory
     *
     * @param
     * @return sqlSessionFactory(SqlSessionFactory)
     */
    @Override
    protected SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
