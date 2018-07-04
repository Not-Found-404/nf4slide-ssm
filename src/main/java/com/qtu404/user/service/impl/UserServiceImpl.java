package com.qtu404.user.service.impl;

import com.qtu404.user.dao.UserDao;
import com.qtu404.user.service.UserService;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.poi.ImageDeal;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserVo> implements UserService {
//    注入userDao
    @Resource(name = "userDao")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

/**
 * 通过手机号获取User
 * @param phoneNum
 * @return UserVo
 * */
    @Override
    public UserVo fetchUserByPhone(String phoneNum) {
        return userDao.fetchUserByPhone(phoneNum);
    }
    /**
     * 通过登陆获取User
     * @param userVo
     * @return UserVo
     * */
    @Override
    public UserVo fetchUserByLogin(UserVo userVo) {
        return userDao.fetchUserByLogin(userVo);
    }
/**
 * 修改用户头像
* @param avatorImgData
 * @param realPath
 * @param userVo
 * @return
* */
    @Override
    public String modifyAvator(UserVo userVo, String avatorImgData, String realPath) {
        String rst = "fail";
        String imgPath = ImageDeal.getAvatorPath(realPath, userVo.getUserId());
        ImageDeal.GenerateImage(avatorImgData, imgPath);
        userVo.setAvator(ImageDeal.getUserAvatorUrl(userVo.getUserId()));
        int changeRow = userDao.modify(userVo);
        if (changeRow == 1) {
            rst = "success";
        }
        return rst;
    }
/**
 * 获取userDao
 * @param
 * @return
 *
 * */
    @Override
    protected BaseDao<UserVo> getBaseDao() {
        return userDao;
    }
}
