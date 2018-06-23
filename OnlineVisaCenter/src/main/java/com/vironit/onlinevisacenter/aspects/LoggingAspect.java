package com.vironit.onlinevisacenter.aspects;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger;

    @AfterReturning("execution(* com.vironit.onlinevisacenter.service..*(..))")
    public void logService(JoinPoint joinPoint){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.info(currentMethod + " of " + currentClass + " was execute successfully");
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service..*(..))",throwing = "e")
    public void logServiceException(JoinPoint joinPoint, Exception e){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(currentMethod + " of " + currentClass + " throw exception: " + e.getMessage(),e);
    }
}
