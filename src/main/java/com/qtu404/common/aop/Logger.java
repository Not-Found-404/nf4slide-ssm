package com.qtu404.common.aop;

import com.qtu404.logger.domain.LogVo;
import com.qtu404.logger.service.LogService;
import com.qtu404.user.domain.UserVo;
import com.qtu404.util.web.ipgetter.IpGetter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class Logger {

    @Autowired
    LogService logService;

    private HttpServletRequest getRequestArg(Object[] args) {
        HttpServletRequest request = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
            }
        }
        return request;
    }

    private String getBodyArg(Object[] args) {
        String request = null;
        for (Object arg : args) {
            if (arg instanceof String) {
                request = (String) arg;
            }
        }
        return request;
    }

    private String getParameter(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Map map = request.getParameterMap();
        for (Object key : map.keySet()) {
            String keyString = (String) key;
            String value = ((String[]) map.get(key))[0];
            if (value.length() <= 50) {
                sb.append(keyString).append(":").append(value).append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public void saveLog(JoinPoint joinPoint) {
        HttpServletRequest request = getRequestArg(joinPoint.getArgs());
        String body = getBodyArg(joinPoint.getArgs());
        Signature signature = joinPoint.getSignature();
        LogVo log = new LogVo();

        if (request != null) {
            String parameter = getParameter(request);
            log.setParameter(parameter);
            UserVo userVo = (UserVo) request.getSession().getAttribute("loginUser");
            if (userVo != null) {
                log.setUsername(userVo.getUsername());
            }
            log.setIpadress(IpGetter.getIpAddress(request));
        } else {
            log.setUsername("null");
            log.setIpadress("null");
        }
        log.setBody(body);
        log.setDate(new Date());
        log.setOperation(signature.getName());
        logService.record(log);
    }
}
