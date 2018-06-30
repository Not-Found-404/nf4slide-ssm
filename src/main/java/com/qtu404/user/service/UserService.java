package com.qtu404.user.service;


import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.service.BaseService;

public interface UserService extends BaseService<UserVo> {

    UserVo fetchUserByPhone(String phoneNum);

    UserVo fetchUserByLogin(UserVo userVo);

    String modifyAvator(UserVo userVo, String avatorImgData, String realPath);

}
