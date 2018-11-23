package com.qtu404.statistics.service;

import com.qtu404.statistics.domain.Attendance;
import com.qtu404.statistics.domain.AttendanceListResponse;

import java.util.List;

public interface AttendanceService {
    Integer save(Attendance attendance);

    List<AttendanceListResponse> list(Attendance attendance);

    String close(Integer attendanceId, Integer userId);
}
