package com.qtu404.logger.service.Impl;

import com.qtu404.logger.dao.LoggerDao;
import com.qtu404.logger.domain.LogVo;
import com.qtu404.logger.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("loggerService")
public class LoggerServiceImpl implements LogService {
    @Resource(name = "loggerDao")
    LoggerDao loggerDao;
    public void record(LogVo log){
        loggerDao.record(log);

    }


}
