package com.qtu404.statistics.service.impl;

import com.qtu404.slide.dao.SlideDao;
import com.qtu404.slide.domain.SlideVo;
import com.qtu404.statistics.dao.AttendanceDao;
import com.qtu404.statistics.domain.Attendance;
import com.qtu404.statistics.domain.AttendanceListResponse;
import com.qtu404.statistics.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDao attendanceDao;

    private final SlideDao slideDao;

    @Autowired
    public AttendanceServiceImpl(AttendanceDao attendanceDao, SlideDao slideDao) {
        this.attendanceDao = attendanceDao;
        this.slideDao = slideDao;
    }

    @Override
    public Integer save(Attendance attendance) {
        attendance.setBeginAt(new Date());
        attendance.setEndAt(new Date(new Date().getTime() + 1000 * 60 * 50));
        SlideVo slideVo = this.slideDao.fetchById(attendance.getSlideId());
        if (slideVo != null) {
            attendance.setSlideName(slideVo.getName());
        }
        return this.attendanceDao.save(attendance).getId();
    }

    @Override
    public List<AttendanceListResponse>
    list(Attendance attendance) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", attendance.getUserId());
        List<AttendanceListResponse> responses = new ArrayList<>();
        List<Attendance> list = this.attendanceDao.listByCondition(condition);
        list.forEach(e -> {
            responses.add(this.model2Response(e));
        });
        return responses;
    }

    @Override
    public String close(Integer attendanceId, Integer userId) {// todo 判断userid是否合法
        Attendance attendance = new Attendance();
        attendance.setEndAt(new Date());
        attendance.setId(attendanceId);
        this.attendanceDao.modify(attendance);
        return "success";
    }

    public AttendanceListResponse model2Response(Attendance attendance) {
        Date beginTime = new Date();
        AttendanceListResponse response = new AttendanceListResponse();
        response.setEndAt(attendance.getBeginAt());
        response.setSlideId(attendance.getSlideId());
        response.setBeginAt(attendance.getBeginAt());
        response.setUserId(attendance.getUserId());
        response.setId(attendance.getId());
        response.setSlideName(attendance.getSlideName());
        System.out.println("single time: " + (new Date().getTime() - beginTime.getTime()) + "ms");
        return response;
    }
}
