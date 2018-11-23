package com.qtu404.statistics.dao;

import com.qtu404.statistics.domain.ViewStatistics;
import com.qtu404.util.web.ssm.dao.BaseDao;

import java.util.List;
import java.util.Map;

public interface ViewStatisticsDao extends BaseDao<ViewStatistics> {
    List<ViewStatistics> listByCondition(Map<String, Object> condition);
}
