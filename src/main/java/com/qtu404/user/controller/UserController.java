package com.qtu404.user.controller;

import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qtu404.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.sms.SMSsender;
import com.qtu404.util.web.Result;
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
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<UserVo> {
    @Resource(name = "userService")
    private UserService userService;
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

        ServletContext sc = request.getServletContext();
        String path = sc.getRealPath("/");

        Result rst = new Result();

        if (userVo != null) {
            Session.setAttribute("usrname", userVo.getUserId());
            Session.setAttribute("loginUser", userVo);
            rst.setResult("loginSuccess");
        } else {
            Session.setAttribute("usrname", "");
            rst.setResult("loginFail");
        }
        writeResult(response, rst);
    }

    /**
     * 用户注册 doRegister
     */
    @RequestMapping("/doRegister")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        UserVo userVo = getDtoObject(request);
        userVo = userService.save(userVo);

        //记录日志
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

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
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
