package com.qtu404.slide.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qtu404.slide.service.SlideService;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("slideAciton")
@RequestMapping("/slide")
public class SlideController extends BaseController<SlideVo> {
    @Resource(name = "slideService")
    private SlideService slideService;

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
     * @param session
     * @param response
     * @param request
     */
    @RequestMapping(value = "/fetchSlideContentById", method = RequestMethod.POST)
    public void fetchSlideContentById(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        SlideVo slideVoDto = getDtoObject(request);
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        SlideVo slideVo = slideService.fetchSlideById(userVo, slideVoDto.getSlideId());
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
        writeResult(response, result);
    }


    public void setSlideService(SlideService slideService) {
        this.slideService = slideService;
    }

    @Override
    protected BaseService<SlideVo> getBaseService() {
        return slideService;
    }

     /*-----------------------  Angular Cli ----------------------------------*/

    /**
     * modifySlide的信息，包括名字，所在文件夹
     *
     * @return
     */
    @RequestMapping("/modifySlideInfoWithAngular")
    public void modifySlideInfo(@RequestBody String body, HttpServletResponse response, HttpServletRequest request) {
        SlideVo dto = JSON.parseObject(body, SlideVo.class);
        HttpSession session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        int rst = slideService.modiyfInfo(userVo, dto);
        Result result = new Result();
        if (rst != 1) {
            result.setResult("modify Success");
            result.setCode(200);
        } else {
            result.setResult("modify fail");
            result.setCode(500);
        }
        writeResult(response, result);
    }
}