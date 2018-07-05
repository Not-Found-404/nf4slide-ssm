package com.qtu404.user.controller;

import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qtu404.logger.domain.LogVo;
import com.qtu404.logger.service.LogService;
import com.qtu404.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.sms.SMSsender;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.ipgetter.IpGetter;
import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<UserVo> {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "loggerService")
    LogService logService;
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 得到当前用户信息
     */
    @RequestMapping(value = "/fetchLoginInfo", method = RequestMethod.POST)
    public void fetchUserLoginInfo(HttpSession session, HttpServletResponse response) throws Exception {
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        writeResult(response, userVo);
    }

    /**
     * 修改头像 modifyUserAvator
     */
    @RequestMapping("/modifyUserAvator")
    public void modifyUserAvator(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException {
        String avatorImgData = request.getParameter("avatorImgData");
        UserVo userVo = (UserVo) session.getAttribute("loginUser");
        String realPath = session.getServletContext().getRealPath("/");
        String[] base64_str = avatorImgData.split(",");
        userService.modifyAvator(userVo, base64_str[1], realPath);
        userVo = userService.fetchById(userVo.getUserId());
        userVo.setPassword("");
        //记录操作
        LogVo log = new LogVo();
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setDate(new Date().toString());
        log.setOperation("modifyUserAvator");
        log.setUsername(userVo.getUsername());
        logService.record(log);
        writeResult(response, userVo);
    }


    /**
     * 验证电话号码是否已被使用 verifyPhone
     */
    @RequestMapping("/verifyPhone")
    public void verifyPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String phone = ((UserVo) getDtoObject(request)).getPhoneNum();
        UserVo userVo = userService.fetchUserByPhone(phone);
        Result result = new Result();
        result.setResult("true");
        if (userVo != null) {//不通过
            result.setResult("false");
        }
        writeResult(response, result);
    }

    /**
     * 用户登陆 login
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession Session) {
        UserVo dto = getDtoObject(request);
        UserVo userVo = userService.fetchUserByLogin(dto);
        //创建log对象
        LogVo log = new LogVo();
        ServletContext sc = request.getServletContext();
        String path = sc.getRealPath("/");

        Result rst = new Result();

        if (userVo != null) {
            Session.setAttribute("usrname", userVo.getUserId());
            Session.setAttribute("loginUser", userVo);
            rst.setResult("loginSuccess");
//           如果登录用户不为空则将当前登录用户的用户名记录到LogVo对象中
            log.setUsername(userVo.getUsername());
            log.setIpadress(IpGetter.getIpAddress(request).toString());
            log.setOperation("login");
            log.setDate(new Date().toString());
        } else {
            Session.setAttribute("usrname", "");
            rst.setResult("loginFail");
        }
        logService.record(log);
        writeResult(response, rst);
    }

    /**
     * 用户注册 doRegister
     */
    @RequestMapping("/doRegister")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        UserVo userVo = getDtoObject(request);
        userVo = userService.save(userVo);
        //创建一个log对象，记录操作
        LogVo log = new LogVo();
        log.setDate(new Date().toString());
        log.setOperation("register");
        log.setIpadress(IpGetter.getIpAddress(request));
        log.setUsername(userVo.getUsername());
        logService.record(log);

        //得到用户IP地址
        String ipAddress = request.getRemoteAddr();

        //发送验证码
        Integer verifyCode = new Random().nextInt(10000);
        try {
            new SMSsender().sendSmsMessage(userVo.getPhoneNum(), String.valueOf(verifyCode));
        } catch (ClientException e) {
            e.printStackTrace();
        }

        //writeResult(response, "success");
        return "home";
    }



    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected BaseService<UserVo> getBaseService() {
        return userService;
    }
}
