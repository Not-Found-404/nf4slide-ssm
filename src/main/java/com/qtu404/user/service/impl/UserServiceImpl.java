package com.qtu404.user.service.impl;

import com.qtu404.folder.dao.FolderDao;
import com.qtu404.folder.domain.Folder;
import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.dao.UserDao;
import com.qtu404.user.service.UserService;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.poi.ImageDeal;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserVo> implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "folderDao")
    private FolderDao folderDao;

    @Resource(name = "slideDao")
    private SlideDao slideDao;

    @Override
    public UserVo fetchUserByPhone(String phoneNum) {
        return userDao.fetchUserByPhone(phoneNum);
    }


    @Override
    public UserVo save(UserVo dto) {
        //创建根路径文件夹
        Folder folder = Folder.createRootFolder();
        folderDao.save(folder);
        dto.setFolderId(folder.getFolderId());
        //调用添加
        super.save(dto);
        // 根据王海涛的要求，要他妈添加默认幻灯片
        SlideVo defaultSlide = slideDao.fetchById(100001);
        if (defaultSlide.getSlideId() != null) {
            defaultSlide.setUserId(dto.getUserId());
            defaultSlide.setExit("true");
            defaultSlide.setFolderId(dto.getFolderId());
            slideDao.save(defaultSlide);
        }
        return dto;
    }


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

    @Override
    protected BaseDao<UserVo> getBaseDao() {
        return userDao;
    }

    /*---------------------------        Angular Folder    -------------------------------------------------*/

    @Override
    public UserVo fetchUserByLogin(UserVo userVo) {
        userVo = userDao.fetchUserByLogin(userVo);
        return userVo;
    }
}
