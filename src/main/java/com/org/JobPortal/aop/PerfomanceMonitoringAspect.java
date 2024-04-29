package com.org.JobPortal.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfomanceMonitoringAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //For Around Advice we need to return an object
    @Around("execution(* com.org.JobPortal.service.JobService.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint jp) throws Throwable {
        int start = (int) System.currentTimeMillis();
        Object obj = jp.proceed();
        int end = (int) System.currentTimeMillis();
        LOGGER.info("Time Taken by : " + jp.getSignature().getName()+" - " + (end-start) + "ms");
        return obj;
    }

}
