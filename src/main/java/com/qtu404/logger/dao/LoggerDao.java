package com.qtu404.logger.dao;

import com.qtu404.logger.domain.LogVo;

public interface LoggerDao {
    void record(LogVo log);
}
