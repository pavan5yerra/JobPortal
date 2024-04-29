package com.org.JobPortal.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);


    @Around("execution(* com.org.JobPortal.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp , int postId) throws Throwable {

        if(postId <0) {
            LOGGER.info("postId is negative " + postId + " updating it");
            postId = -postId;
        }
        Object obj = jp.proceed(new Object[]{postId});
        LOGGER.info("postId updated to positive :" +  postId);
        return obj;

    }
}
