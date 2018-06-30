package com.qtu404.util.web.ssm.dao;

import java.util.List;


public interface BaseDao<T> {

    /**
     * 按照id得到一个实体对象
     *
     * @param id
     * @return 单个实体对象
     */
    T fetchById(int id);

    /**
     * 获得所有实体对象
     *
     * @return 实体对象list
     */
    List<T> findAll();

    /**
     * 添加一个实体的实例
     *
     * @param t
     * @return 数据库改变的行数
     */
    T save(T t);

    /**
     * 修改一个实体
     *
     * @param t
     * @return
     */
    int modify(T t);

    /**
     * 删除一个实体对象实例
     * @param t
     * @return
     */
    int delete(T t);
}
