package com.qtu404.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logger {

    @Pointcut("execution(* com.qtu404.slide.service.impl.SlideServiceImpl.*(..))")
    private void testLoggerPointcut() {
        System.out.println("test");
    }


    @Before("testLoggerPointcut()")
    public void testLogger() {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }
}
