package com.qtu404.statistics.dao;

import com.qtu404.statistics.domain.Attendance;
import com.qtu404.util.web.ssm.dao.BaseDao;

import java.util.List;
import java.util.Map;

public interface AttendanceDao extends BaseDao<Attendance> {
    List<Attendance> listByCondition(Map<String, Object> condition);
}
