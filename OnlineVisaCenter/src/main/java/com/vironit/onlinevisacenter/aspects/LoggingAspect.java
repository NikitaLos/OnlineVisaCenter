package com.vironit.onlinevisacenter.aspects;

import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger;

    @After("execution(* com.vironit.onlinevisacenter.service.ApplicationServiceImpl.*(..))")
    public void logApplicationService(JoinPoint joinPoint){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.info(currentMethod + " of " + currentClass + " was execute successfully");
    }

    @After("execution(* com.vironit.onlinevisacenter.service.CountryServiceImpl.*(..))")
    public void logCountryService(JoinPoint joinPoint){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.info(currentMethod + " of " + currentClass + " was execute successfully");
    }

    @After("execution(* com.vironit.onlinevisacenter.service.DocumentServiceImpl.*(..))")
    public void logDocumentService(JoinPoint joinPoint){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.info(currentMethod + " of " + currentClass + " was execute successfully");
    }

    @After("execution(* com.vironit.onlinevisacenter.service.EmailSenderServiceImpl.*(..))")
    public void logSenderService(JoinPoint joinPoint){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.info(currentMethod + " of " + currentClass + " was execute successfully");
    }

    @After("execution(* com.vironit.onlinevisacenter.service.UserServiceImpl.*(..))")
    public void logUserService(JoinPoint joinPoint){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.info(currentMethod + " of " + currentClass + " was execute successfully");
    }

    @After("execution(* com.vironit.onlinevisacenter.service.VisaServiceImpl.*(..))")
    public void logVisaService(JoinPoint joinPoint){
        String currentMethod = joinPoint.getSignature().toShortString();
        String currentClass = joinPoint.getTarget().getClass().toString();
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.info(currentMethod + " of " + currentClass + " was execute successfully");
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service.CountryServiceImpl.*(..))",throwing = "e")
    public void logCountryServiceException(JoinPoint joinPoint, CountryServiceException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service.DocumentServiceImpl.*(..))",throwing = "e")
    public void logDocumentServiceException(JoinPoint joinPoint,DocumentServiceException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service.EmailSenderServiceImpl.*(..))",throwing = "e")
    public void logEmailSenderException(JoinPoint joinPoint, SenderServiceException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service.UserServiceImpl.*(..))",throwing = "e")
    public void logUserServiceException(JoinPoint joinPoint,UserServiceException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service.VisaServiceImpl.*(..))",throwing = "e")
    public void logVisaServiceException(JoinPoint joinPoint,VisaServiceException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service.ApplicationServiceImpl.*(..))",throwing = "e")
    public void logApplicationServiceException(JoinPoint joinPoint,ApplicationServiceException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service.UserServiceImpl.*(..))",throwing = "e")
    public void logLoginException(JoinPoint joinPoint,LoginationException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }

    @AfterThrowing(value = "execution(* com.vironit.onlinevisacenter.service..*(..))",throwing = "e")
    public void logDuplicateException(JoinPoint joinPoint,DuplicateException e){
        logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        logger.error(e.getMessage(),e);
    }
}
