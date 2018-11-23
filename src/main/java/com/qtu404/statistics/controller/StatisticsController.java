package com.qtu404.statistics.controller;

import com.qtu404.statistics.domain.Attendance;
import com.qtu404.statistics.domain.AttendanceListResponse;
import com.qtu404.statistics.domain.ViewStatistics;
import com.qtu404.statistics.service.AttendanceService;
import com.qtu404.statistics.service.ViewStatisticsService;
import com.qtu404.user.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/statistics/")
public class StatisticsController {
    private final AttendanceService attendanceService;

    private final ViewStatisticsService viewStatisticsService;

    @GetMapping("attendance/save")
    public Integer attendanceSave(@RequestParam Integer slideId, HttpSession session) {
        Attendance attendance = new Attendance();
        attendance.setUserId(((UserVo) session.getAttribute("loginUser")).getUserId());
        attendance.setSlideId(slideId);
        return this.attendanceService.save(attendance);
    }


    @GetMapping("attendance/list")
    public List<AttendanceListResponse> attendanceList(HttpSession session) {
        Attendance attendance = new Attendance();
        attendance.setUserId(((UserVo) session.getAttribute("loginUser")).getUserId());
        return this.attendanceService.list(attendance);
    }

    @GetMapping("view/list")
    public List<ViewStatistics> viewList(@RequestParam Integer attendanceId) {
        return this.viewStatisticsService.findByAttendanceId(attendanceId);
    }

    @GetMapping("view/saveOrUpdate")
    public String viewSaveOrUpdate(@RequestParam Integer exitTime, @RequestParam Long totalTime, @RequestParam Integer attendanceId, @RequestParam String identify) {
        ViewStatistics viewStatistics = new ViewStatistics();
        viewStatistics.setAttendanceId(attendanceId);
        viewStatistics.setExitTimes(exitTime);
        viewStatistics.setTotalTime(totalTime);
        viewStatistics.setIdentify(identify);
        return this.viewStatisticsService.saveOrUpdate(viewStatistics);
    }

    @GetMapping("attendance/close")
    public String attendanceClose(@RequestParam Integer attendanceId, HttpSession session) {
        return this.attendanceService.close(attendanceId, ((UserVo) session.getAttribute("loginUser")).getUserId());
    }

    @Autowired
    public StatisticsController(AttendanceService attendanceService, ViewStatisticsService viewStatisticsService) {
        this.attendanceService = attendanceService;
        this.viewStatisticsService = viewStatisticsService;
    }

}
