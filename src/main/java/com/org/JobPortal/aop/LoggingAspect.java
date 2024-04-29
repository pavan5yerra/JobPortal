package com.org.JobPortal.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {


    private static final Logger  LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

//    Syntax execution(return type  fullyQualified className.methodName(parameters))
//    return type  --> * or specific return type
//    Fully Qualified class name ---> com.org.JobPortal.service.JobService
//    method ---> .* or .methodName
//    parameters ---> (..) or specific parameters
//    inside advice methods we can also use multiple execution statement
//    @Before( execution(* com.org.JobPortal.service.JobService.getJob(..)) || execution(* com.org.JobPortal.service.JobService.updtaeJob(..)))

    @Before("execution(* com.org.JobPortal.service.JobService.getJob(..))")
    //JoinPoint which give info which method you're trying to execute
    public void  logMethodCall(JoinPoint jp) {
        LOGGER.info("Method called: "+ jp.getSignature().getName());
    }

    @After("execution(* com.org.JobPortal.service.JobService.getJob(..))")
    public void  logMethodExecuted(JoinPoint jp) {
        LOGGER.info("Method Executed: "+ jp.getSignature().getName());
    }
    @AfterThrowing("execution(* com.org.JobPortal.service.JobService.getJob(..))")
    public void  logMethodCrash(JoinPoint jp) {
        LOGGER.info("Method Crashed"+ jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.org.JobPortal.service.JobService.getJob(..))")
    public void  logMethodExecutedSuccess(JoinPoint jp) {
        LOGGER.info("Method Executed Succesfully :"+ jp.getSignature().getName());
    }

}
