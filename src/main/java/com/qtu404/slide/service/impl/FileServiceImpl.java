package com.qtu404.slide.service.impl;

import com.qtu404.common.ImgToSlide;
import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.service.FileService;
import com.qtu404.slide.domain.FileVo;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.dao.UserDao;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.poi.PPTReader;
import com.qtu404.util.web.ssm.dao.BaseDao;
import com.qtu404.util.web.ssm.service.BaseServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wildhunt_unique
 */
@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl<FileVo> implements FileService {
    @Resource(name = "slideDao")
    private SlideDao slideDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public SlideVo savePPTToSlide(FileVo fileVo) {
        // Create a slide with default template
        SlideVo slideVo = SlideVo.createNewSlide(fileVo.getUserId());

        // Modify the name of file
        slideVo.setName(fileVo.getFileName());
        UserVo userVo = userDao.fetchById(slideVo.getUserId());
        if (userVo != null) {
            slideVo.setFolderId(userVo.getFolderId());
        }
        // Save the slide to database by call the method named save() of slideDao
        slideVo = slideDao.save(slideVo);

        // Get the physical file path of the PPTX file
        String realSaveDirPath = fileVo.getContextPath() + File.separator
                + fileVo.getFileSaveDirPath() + File.separator
                + fileVo.getUserId() + File.separator;
        PPTReader pptReader = new PPTReader();
        pptReader.setScale(1);

        // Set the output path, all of images will be saved in this directory
        pptReader.setOutput_path(realSaveDirPath);
        pptReader.setPPT_file_path(fileVo.getRealPath());

        // Translate the pptx format to img format
        // It will be take a lof of time
        List<String> list = pptReader.ppt2png(String.valueOf(slideVo.getSlideId()));
        ImgToSlide imgToSlide = new ImgToSlide(list, fileVo);
        String content = imgToSlide.jpegToImgContent();
        String play = imgToSlide.jpegToImgPlay();

        slideVo.setContent(content);
        slideVo.setPlay(play);

        // call the method modify of slideDao to upadte the slide
        slideDao.modify(slideVo);
        return slideVo;
    }


    @Override
    public String saveFile(FileVo fileVo) {
        String result = null;
        //存储文件的真实根目录
        String realDir = fileVo.getContextPath() + File.separator + fileVo.getFileSaveDirPath();
        //存储在该用户的文件夹下
        realDir = realDir + File.separator + fileVo.getUserId();
        //创建要存图片的文件夹
        File saveDir = new File(realDir);
        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if (!saveDir.exists()) saveDir.mkdirs();

        //创建要存储的文件
        File saveFile = new File(realDir, fileVo.getFileName());
        try {
            //保存文件
            fileVo.getUploadFile().transferTo(saveFile);
            //得到img标记对可以用的相对路径
            result = fileVo.getFileSavePath();
        } catch (IOException e) {
            result = "upLoadImgFail";
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected BaseDao<FileVo> getBaseDao() {
        return null;
    }
}
