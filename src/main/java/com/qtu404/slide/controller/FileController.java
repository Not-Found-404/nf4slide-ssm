package com.qtu404.slide.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.logger.domain.LogVo;
import com.qtu404.logger.service.LogService;
import com.qtu404.slide.service.FileService;
import com.qtu404.slide.service.SlideService;
import com.qtu404.slide.domain.FileVo;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.ipgetter.IpGetter;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
@RequestMapping("/file")
public class FileController extends BaseController<FileVo> {
    @Resource(name = "slideService")
    private SlideService slideService;

    @Resource(name = "fileService")
    private FileService fileService;

    @Resource(name = "loggerService")
    LogService logService;

    private final String imgFileDateBase = "ImgFileDateBase";

    private final String pptSaveDirPath = "PPTXFileDateBase";
    private Result result = new Result();//json response

    private ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 上传pptx
     *
     * @param uploadFile
     * @param request
     * @param response
     */
    @RequestMapping(value = "/pptUpLoad", method = RequestMethod.POST)
    public void slidePPTXUpLoad(@RequestParam(value = "file", required = false) MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {
        //上下文获取
        HttpSession session = request.getSession();
        //各项参数获取以及封装
        UserVo loginUser = (UserVo) session.getAttribute("loginUser");
        String realPath = request.getServletContext().getRealPath("");


        FileVo fileVo = new FileVo();
        fileVo.setUploadFile(uploadFile);
        fileVo.setFileContentType(request.getParameter(("xtype")));
        fileVo.setFileName(request.getParameter(("name")));
        fileVo.setSize(request.getParameter(("size")));
        fileVo.setFileSaveDirPath(pptSaveDirPath);
        fileVo.setUserId(loginUser.getUserId());
        fileVo.setContextPath(realPath);
        fileService.saveFileToLocal(fileVo);
        SlideVo slideVo = fileService.savePPTToSlide(fileVo);

        //reduce the burden of network transmission
        slideVo.setContent("");
        slideVo.setPlay("");
        writeResult(response, slideVo);
    }


    /**
     * 保存上传的ppt里的图片
     * 成功则发回图片文件可以用的相对路径
     *
     * @param uploadFile
     * @param response
     * @param request
     */
    @RequestMapping(value = "/slideImgUpLoad", method = RequestMethod.POST)
    public void slideImgUpLoad(@RequestParam(value = "file", required = false) MultipartFile uploadFile, HttpServletResponse response, HttpServletRequest request) {
        //上下文获取
        HttpSession session = request.getSession();
        //各项参数获取以及封装
        String realPath = request.getServletContext().getRealPath("");
        UserVo loginUser = (UserVo) session.getAttribute("loginUser");


        FileVo fileVo = new FileVo();
        fileVo.setUploadFile(uploadFile);
        fileVo.setFileContentType(request.getParameter(("type")));
        fileVo.setFileName(request.getParameter(("name")));
        fileVo.setSize(request.getParameter(("size")));
        fileVo.setFileSaveDirPath(imgFileDateBase);
        fileVo.setUserId(loginUser.getUserId());
        fileVo.setContextPath(realPath);

        result.setResult(fileService.saveFile(fileVo));

        //记录操作
        writeResult(response, result);
    }

    @Override
    protected BaseService<FileVo> getBaseService() {
        return fileService;
    }
}
