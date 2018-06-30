package com.qtu404.slide.dao;


import com.qtu404.slide.domain.SlideVo;
import com.qtu404.util.web.ssm.dao.BaseDao;

import java.util.List;

public interface SlideDao extends BaseDao<SlideVo> {
    public List<SlideVo> findAllSlideByUserId(Integer userId);
}
