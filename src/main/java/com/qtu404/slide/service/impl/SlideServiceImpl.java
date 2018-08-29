package com.qtu404.slide.service.impl;
/**
 *
 */

import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.service.SlideService;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("slideService")
public class SlideServiceImpl extends BaseServiceImpl<SlideVo> implements SlideService {
    @Resource(name = "slideDao")
    private SlideDao slideDao;

    /**
     * 修改幻灯片内容
     *
     * @param userVo
     * @param slideParm
     * @return
     */
    @Override
    public int modifyContent(UserVo userVo, SlideVo slideParm) {
        //查出来的幻灯片
        SlideVo slideVo = slideDao.fetchById(slideParm.getSlideId());
        Integer userId = slideVo.getUserId();
        if (userId.equals(userVo.getUserId())) {//保证是自己的
            slideVo.setContent(slideParm.getContent());//更新编辑内容
            slideVo.setPlay(slideParm.getPlay());//更新播放内容
            slideVo.setTheme(slideParm.getTheme());//更新主题
            slideVo.setConfig(slideParm.getConfig());//更新设置内容
            return slideDao.modify(slideVo);
        } else {
            return 0;
        }
    }

    /**
     * 添加一新的幻灯片
     *
     * @param userId
     * @return
     */
    @Override
    public SlideVo addNewSlide(Integer userId) {
        SlideVo slideVo = SlideVo.createNewSlide(userId);
        slideDao.save(slideVo);
        int rst = slideVo.getSlideId();
        return slideVo;
    }


    @Override
    public SlideVo fetchSlideById(UserVo userVo, Integer slideId) {
        SlideVo slideVo = slideDao.fetchById(slideId);
        if (slideVo != null) {
            if (userVo != null && userVo.getUserId().equals(slideVo.getUserId())) {
                slideVo.setWhoPlay("owner");
            } else {
                slideVo.setWhoPlay("visitor");
            }
        } else {
            return null;
        }
        return slideVo;
    }

    @Override
    public int delSlideById(UserVo userVo, Integer slideId) {
        SlideVo slideVo = slideDao.fetchById(slideId);
        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            slideVo.setExit("false");
            return slideDao.modify(slideVo);
        } else {
            return 0;
        }
    }

    @Override
    public int modifySlideName(UserVo userVo, Integer slideId, String slideName) {
        SlideVo slideVo = slideDao.fetchById(slideId);
        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            slideVo.setName(slideName);
            return slideDao.modify(slideVo);
        } else {
            return 0;
        }
    }

    @Override
    public int modifySlideFolder(UserVo userVo, Integer slideId, Integer folderId) {
        SlideVo slideVo = slideDao.fetchById(slideId);
        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            slideVo.setFolderId(folderId);
            return slideDao.modify(slideVo);
        } else {
            return 0;
        }
    }

    @Override
    public int modiyfInfo(UserVo userVo, SlideVo dto) {
        SlideVo slideVo = slideDao.fetchById(dto.getSlideId());
        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            slideVo.setName(dto.getName());
            slideVo.setFolderId(dto.getFolderId());
            return slideDao.modifyInfo(slideVo);
        } else {
            return 0;
        }
    }

    @Override
    public SlideVo addNewSlide(Integer userId, Integer folderId) {
        SlideVo slideVo = SlideVo.createNewSlide(userId);
        slideVo.setFolderId(folderId);
        slideDao.save(slideVo);
        int rst = slideVo.getSlideId();
        return slideVo;
    }

    @Override
    public List<SlideVo> findByName(SlideVo slideVo) {
        return this.slideDao.findByName(slideVo);
    }

    public SlideDao getSlideDao() {
        return slideDao;
    }

    public void setSlideDao(SlideDao slideDao) {
        this.slideDao = slideDao;
    }

    @Override
    public List<SlideVo> findAllSlideByUserId(Integer userId) {
        return slideDao.findAllSlideByUserId(userId);
    }

    @Override
    protected BaseDao<SlideVo> getBaseDao() {
        return slideDao;
    }
}
