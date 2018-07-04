package com.qtu404.util.web.ssm.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.annotation.Resource;
import java.util.List;

/**
 * 需要重写getNamespaces()方法得到namespace
 *
 * @param <T> 要操作的类
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    protected String delToken = "delete";
    protected String modifyToken = "modify";
    protected String addOneToken = "save";
    protected String getAllToken = "findAll";
    protected String getByIdToken = "fetchById";
@Resource(name = "sqlSessionFactory")
SqlSessionFactory sqlSessionFactory;
    protected abstract String getNamespaces();

    protected abstract SqlSessionFactory getSqlSessionFactory();


    @Override
    public T fetchById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        T t = (T) sqlSession.selectOne(getNamespaces() + "." + getByIdToken, id);
        sqlSession.commit();
        sqlSession.close();
        return t;
    }

    @Override
    public List<T> findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<T> t_list = sqlSession.selectList(getNamespaces() + "." + getAllToken);

        sqlSession.commit();
        sqlSession.close();
        return t_list;
    }

    @Override
    public T save(T t) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int rst = sqlSession.insert(getNamespaces() + "." + addOneToken, t);

        sqlSession.commit();
        sqlSession.close();
        return t;
    }

    @Override
    public int modify(T t) {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        int rst = sqlSession.update(getNamespaces() + "." + modifyToken, t);

        sqlSession.commit();
        sqlSession.close();
        return rst;
    }

    @Override
    public int delete(T t) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int rst = sqlSession.delete(getNamespaces() + "." + delToken, t);

        sqlSession.commit();
        sqlSession.close();
        return rst;
    }
}
