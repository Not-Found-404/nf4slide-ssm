package com.qtu404.statistics.service.impl;

import com.qtu404.statistics.dao.AttendanceDao;
import com.qtu404.statistics.dao.ViewStatisticsDao;
import com.qtu404.statistics.domain.Attendance;
import com.qtu404.statistics.domain.ViewStatistics;
import com.qtu404.statistics.service.ViewStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ViewStatisticsServiceImpl implements ViewStatisticsService {
    private final ViewStatisticsDao viewStatisticsDao;
    private final AttendanceDao attendanceDao;

    @Autowired
    public ViewStatisticsServiceImpl(ViewStatisticsDao viewStatisticsDao, AttendanceDao attendanceDao) {
        this.viewStatisticsDao = viewStatisticsDao;
        this.attendanceDao = attendanceDao;
    }

    @Override
    public List<ViewStatistics> findByAttendanceId(Integer attendanceId) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("attendanceId", attendanceId);
        return this.viewStatisticsDao.listByCondition(condition);
    }

    @Override
    public String saveOrUpdate(ViewStatistics request) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("attendanceId", request.getAttendanceId());
        condition.put("identify", request.getIdentify());
        List<ViewStatistics> list = this.viewStatisticsDao.listByCondition(condition);
        if (list != null && list.size() == 1) {
            ViewStatistics viewStatistics = list.get(0);
            Integer attendanceId = viewStatistics.getAttendanceId();
            Attendance attendance = this.attendanceDao.fetchById(attendanceId);

            System.out.println(new Date().getTime());
            System.out.println(attendance.getEndAt().getTime());

            if (attendance.getEndAt().getTime() <= new Date().getTime()) {
                return "timeout";
            }
            viewStatistics.setTotalTime(viewStatistics.getTotalTime() + request.getTotalTime());
            viewStatistics.setExitTimes(viewStatistics.getExitTimes() + request.getExitTimes());
            this.viewStatisticsDao.modify(viewStatistics);
            return "update success";
        } else {
            this.viewStatisticsDao.save(request);
            return "save success";
        }
    }

}
