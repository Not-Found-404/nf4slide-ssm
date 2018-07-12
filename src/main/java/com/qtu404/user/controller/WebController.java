package com.qtu404.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtu404.util.web.ssm.controller.BaseController;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 用于处理路由
 * @author Lichun
 *
 */
@Controller
@RequestMapping("/web")
public class WebController extends BaseController {
    /**
     * 默认处理/web下说有的请求，全部转发到index.html
     * @param request
     * @param response
     */
    @RequestMapping("**")
    public void routes(HttpServletRequest request ,HttpServletResponse response) {
        request.setAttribute("routes","路由跳转");
        try {
            // 此处路径要打两点，如果直接写 index.html 会循环反问/web/index.html 造成死循环
            request.getRequestDispatcher("../index.html").forward(request,response);
        } catch (Exception es) {
//            log.error("路由失败",es);
        }
    }

    @Override
    protected BaseService getBaseService() {
        return null;
    }
}