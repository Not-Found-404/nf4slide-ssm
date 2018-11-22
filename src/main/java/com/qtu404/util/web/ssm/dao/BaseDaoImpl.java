package com.qtu404.util.web.ssm.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
    //注入sqlSessionFactory
    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    protected abstract String getNamespaces();

    protected abstract SqlSessionFactory getSqlSessionFactory();

    /**
     * 从数据库通过id获取一个实体
     *
     * @param id(int)
     * @return T
     */
    @Override
    public T fetchById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        T t = (T) sqlSession.selectOne(getNamespaces() + "." + getByIdToken, id);
        sqlSession.commit();
        sqlSession.close();
        return t;
    }

    /**
     * 从数据库获取某类型全部实体
     *
     * @param
     * @return List<T>
     */
    @Override
    public List<T> findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<T> t_list = sqlSession.selectList(getNamespaces() + "." + getAllToken);

        sqlSession.commit();
        sqlSession.close();
        return t_list;
    }

    /**
     * 将一个实体保存到数据库
     *
     * @param t
     * @return T
     */
    @Override
    public T save(T t) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int rst = sqlSession.insert(getNamespaces() + "." + addOneToken, t);

        sqlSession.commit();
        sqlSession.close();
        return t;
    }

    /**
     * 修改数据库中的实体数据
     *
     * @param t
     * @return int
     */
    @Override
    public int modify(T t) {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        int rst = sqlSession.update(getNamespaces() + "." + modifyToken, t);

        sqlSession.commit();
        sqlSession.close();
        return rst;
    }

    /**
     * 删除数据库中的数据记录
     *
     * @param t
     * @return int
     */
    @Override
    public int delete(T t) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int rst = sqlSession.delete(getNamespaces() + "." + delToken, t);

        sqlSession.commit();
        sqlSession.close();
        return rst;
    }
}
