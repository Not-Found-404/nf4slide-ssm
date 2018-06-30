package com.qtu404.util.web.ssm.service;

import com.qtu404.util.web.ssm.dao.BaseDao;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseDao<T> getBaseDao();

    @Override
    public T fetchById(int id) {
        return getBaseDao().fetchById(id);
    }

    @Override
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public T save(T t) {
        return getBaseDao().save(t);
    }

    @Override
    public int modify(T t) {
        return getBaseDao().modify(t);
    }

    @Override
    public int delete(T t) {
        return getBaseDao().delete(t);
    }
}
