package com.qtu404.slide.service;


import com.qtu404.slide.domain.FileVo;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.util.web.ssm.service.BaseService;

public interface FileService extends BaseService<FileVo> {
    public String saveFile(FileVo file);

    public SlideVo savePPTToSlide(FileVo fileVo);
}
