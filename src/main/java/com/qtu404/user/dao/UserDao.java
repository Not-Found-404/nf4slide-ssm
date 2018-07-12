package com.qtu404.user.dao;

import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.dao.BaseDao;

public interface UserDao extends BaseDao<UserVo> {
    public UserVo fetchUserByPhone(String phoneNum);

    public UserVo fetchUserByLogin(UserVo userVo);

}
