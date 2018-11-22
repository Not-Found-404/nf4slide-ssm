package com.qtu404.statistics.service.impl;

import com.qtu404.statistics.dao.AttendanceDao;
import com.qtu404.statistics.domain.Attendance;
import com.qtu404.statistics.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDao attendanceDao;

    @Autowired
    public AttendanceServiceImpl(AttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @Override
    public Integer save(Attendance attendance) {
        attendance.setBeginAt(new Date());
        attendance.setBeginAt(new Date(new Date().getTime() + 1000 * 60 * 50));
        return this.attendanceDao.save(attendance).getId();
    }
}
