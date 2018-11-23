package com.qtu404.statistics.service;

import com.qtu404.statistics.domain.ViewStatistics;

import java.util.List;

public interface ViewStatisticsService {
    List<ViewStatistics> findByAttendanceId(Integer attendanceId);

    String saveOrUpdate(ViewStatistics viewStatistics);
}
