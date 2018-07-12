package com.qtu404.slide.service;


import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ssm.service.BaseService;

import java.util.List;

public interface SlideService extends BaseService<SlideVo> {
    public int modifyContent(UserVo userVo, SlideVo slideVo);

    /**
     * 返回被添加的slideid
     */
    public SlideVo addNewSlide(Integer userId);

    public SlideVo fetchSlideById(UserVo userVo, Integer slideId);

    public int delSlideById(UserVo userVo, Integer slideId);

    public int modifySlideName(UserVo userVo, Integer sildeId, String slideName);

    int modifySlideFolder(UserVo userVo, Integer slideId, Integer folderId);

    int modiyfInfo(UserVo userVo, SlideVo slideVo);

    SlideVo addNewSlide(Integer userId, Integer folderId);

    List<SlideVo> findByName(SlideVo slideVo);

    public List<SlideVo> findAllSlideByUserId(Integer userId);

}
