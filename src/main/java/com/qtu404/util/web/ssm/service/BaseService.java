package com.qtu404.util.web.ssm.service;

import java.util.List;

public interface BaseService<T> {

    /**
     * 得到单个实体
     *
     * @param id
     * @return
     */
    T fetchById(int id);

    /**
     * 得到所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 添加一个实体
     *
     * @param t
     * @return
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
     * 删除一个实体
     *
     * @param t
     * @return
     */
    int delete(T t);

}
