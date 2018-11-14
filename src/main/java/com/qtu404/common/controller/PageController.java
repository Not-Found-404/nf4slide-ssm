package com.qtu404.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PageController {
    @RequestMapping("/toRegisterPage")
    public String toRegisterPage() {
        return "registerA";
    }

    @RequestMapping("/toUserPage")
    public String toUserPage() {
        return "usercenterA";
    }

    @RequestMapping("/toSlideEditPage")
    public String toSlideEditPage() {
        return "edit";
    }

    @RequestMapping("/toPlayPage")
    public String toSlidePlayPage() {
        return "playSlide";
    }

    @RequestMapping("/toAvatorEditpage")
    public String toAvatorEditpage() {
        return "avator";
    }

    @RequestMapping("/toHomePage")
    public String toHomePage(HttpSession session) {
        if (session != null && session.getAttribute("usrname") != null) {
            return "usercenterA";
        }
        return "homeA";
    }

    @RequestMapping("/toUploadPage")
    public String toUploadPage() {
        return "upload";
    }

    @RequestMapping("/toPublishPage")
    public String toPublishPage() {
        return "publishQuestion";
    }
}
