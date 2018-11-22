package com.qtu404.statistics.controller;

import com.qtu404.statistics.domain.Attendance;
import com.qtu404.statistics.service.AttendanceService;
import com.qtu404.user.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Controller
@RequestMapping("/api/statistics/")
public class StatisticsController {
    private final AttendanceService attendanceService;

    @RequestMapping("attendance/save")
    public Integer save(@RequestParam Integer slideId, HttpServletRequest request) {
        Attendance attendance = new Attendance();
        UserVo login = (UserVo) request.getSession().getAttribute("loginUser");
        // attendance.setUserId(((UserVo) session.getAttribute("loginUser")).getUserId());
        return this.attendanceService.save(attendance);
    }

    @Autowired
    public StatisticsController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
}
