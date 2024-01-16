package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
  private static final String LOG_BEFORE_CALL_METHOD = "Start method: ";
  private static final String LOG_AFTER_CALL_METHOD = "Finish method: ";

  //  @Before(value = "com.example.aspect.Pointcuts.allMethods()")
  @Before(value = "execution(* com.example.api..*(..))")
  public void logBeforeMethod(JoinPoint joinPoint) {
    commonLog(joinPoint, LOG_BEFORE_CALL_METHOD);
  }

//  @After(value = "com.example.aspect.Pointcuts.allMethods()")
  @After(value = "execution(* com.example.api..*(..))")
  public void logAfterMethod(JoinPoint joinPoint) {
    commonLog(joinPoint, LOG_AFTER_CALL_METHOD);
  }

  private void commonLog(JoinPoint joinPoint, String message) {
    final String className = joinPoint.getTarget().getClass().getName();
    final Logger logger = LoggerFactory.getLogger(className);
    final String methodName = joinPoint.getSignature().getName();
    logger.info(message + methodName);
  }

  @Before(value = "execution(* com.example.database..*(..))")
  public void logBeforeDatabaseMethod(JoinPoint joinPoint) {
    commonLog(joinPoint, LOG_BEFORE_CALL_METHOD);
  }

  @After(value = "execution(* com.example.database..*(..))")
  public void logAfterDatabaseMethod(JoinPoint joinPoint) {
    commonLog(joinPoint, LOG_AFTER_CALL_METHOD);
  }

  @Before(value = "execution(* com.example.mapper..*(..))")
  public void logBeforeMapperMethod(JoinPoint joinPoint) {
    commonLog(joinPoint, LOG_BEFORE_CALL_METHOD);
  }

  @After(value = "execution(* com.example.mapper..*(..))")
  public void logAfterMapperMethod(JoinPoint joinPoint) {
    commonLog(joinPoint, LOG_AFTER_CALL_METHOD);
  }
}
