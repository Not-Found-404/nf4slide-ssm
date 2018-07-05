package com.qtu404.slide.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qtu404.logger.domain.LogVo;
import com.qtu404.logger.service.LogService;
import com.qtu404.slide.service.SlideService;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.ipgetter.IpGetter;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller("slideAciton")
@RequestMapping("/slide")
public class SlideController extends BaseController<SlideVo> {
    @Resource(name = "slideService")
    private SlideService slideService;

    //注入logService
    @Resource(name = "loggerService")
    LogService logService;

    /**
     * findAllSlideByLogin
     */
    @RequestMapping("/findAllSlideByLogin")
    public void findAllSlideByLogin(HttpSession session, HttpServletResponse response) throws JsonProcessingException {
        UserVo loginUser = (UserVo) session.getAttribute("loginUser");
        List<SlideVo> slideVos = slideService.findAllSlideByUserId(loginUser.getUserId());
        writeResult(response, slideVos);
    }

    /**
     *
     * @param session
     * @param response
     * @param request
     */
    @RequestMapping(value = "/fetchSlideContentById", method = RequestMethod.POST)
    public void fetchSlideContentById(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        SlideVo slideVoDto = getDtoObject(request);
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        SlideVo slideVo = slideService.fetchSlideById(userVo, slideVoDto.getSlideId());
        //记录操作
        LogVo log = new LogVo();
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setDate(new Date().toString());
        log.setOperation("fetchSlideContent");
        log.setUsername(userVo.getUsername());
        logService.record(log);
        writeResult(response, slideVo);
    }

    /**
     * fetchSlidePlayById
     *
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/fetchSlidePlayById")
    public void fetchSlidePlayById(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException {
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        SlideVo dto = getDtoObject(request);
        SlideVo slideVo = slideService.fetchSlideById(userVo, dto.getSlideId());
        slideVo.setContent("");
        //记录操作
        LogVo log = new LogVo();
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setDate(new Date().toString());
        log.setOperation("fetchSlidePlay");
        log.setUsername(userVo.getUsername());
        logService.record(log);
        writeResult(response, slideVo);
    }

    /**
     * addNewSlide (有问题)
     *
     * @return
     */
    @RequestMapping("/addNewSlide")
    public void addNewSlide(HttpServletResponse response, HttpServletRequest request) {
        setJSONTypeResponeHander(response);
        HttpSession session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        SlideVo slideVo = slideService.addNewSlide(userVo.getUserId());
        //记录操作
        LogVo log = new LogVo();
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setDate(new Date().toString());
        log.setOperation("addNewSlide");
        log.setUsername(userVo.getUsername());
        logService.record(log);
        writeResult(response, slideVo);
    }

    /**
     * modifySlideName
     *
     * @return
     */
    @RequestMapping("/modifySlideName")
    public void modifySlideName(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        SlideVo dto = getDtoObject(request);
        slideService.modifySlideName(userVo, dto.getSlideId(), dto.getName());
        //记录操作
        LogVo log = new LogVo();
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setDate(new Date().toString());
        log.setOperation("modifySlideName");
        log.setUsername(userVo.getUsername());
        logService.record(log);
    }

    /**
     * delSlide
     *
     * @return
     */
    @RequestMapping("/delSlide")
    public void delSilde(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SlideVo dto = getDtoObject(request);
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        int rst = slideService.delSlideById(userVo, dto.getSlideId());
        Result result = new Result();
        if (rst == 1) {
            result.setResult("delSuccess");
        } else {
            result.setResult("delFail");
        }
        //记录操作
        LogVo log = new LogVo();
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setDate(new Date().toString());
        log.setOperation("delSlide");
        log.setUsername(userVo.getUsername());
        logService.record(log);
        writeResult(response, result);
    }

    /**
     * modifySlideContent (有问题)
     *
     * @return
     */
    @RequestMapping(value = "/modifySlide", method = RequestMethod.POST)
    public void modifySlideContent(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SlideVo slideVo = getDtoObject(request);
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        int rst = slideService.modifyContent(userVo, slideVo);
        Result result = new Result();
        if (rst == 1) {
            result.setResult("modifySuccess");
        } else {
            result.setResult("modifyFail");
        }
        //记录操作
        LogVo log = new LogVo();
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setDate(new Date().toString());
        log.setOperation("modifySlide");
        log.setUsername(userVo.getUsername());
        logService.record(log);
        writeResult(response, result);
    }


    public void setSlideService(SlideService slideService) {
        this.slideService = slideService;
    }

    @Override
    protected BaseService<SlideVo> getBaseService() {
        return slideService;
    }
}