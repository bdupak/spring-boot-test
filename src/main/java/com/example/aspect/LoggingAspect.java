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
  private Logger logger;
  private String className;
  private String methodName;

//  @Before(value = "com.example.aspect.Pointcuts.allMethods()")
  @Before(value = "execution(* com.example.api..*(..))")
  public void logBeforeMethod(JoinPoint joinPoint) {
    className = joinPoint.getTarget().getClass().getName();
    logger = LoggerFactory.getLogger(className);
    methodName = joinPoint.getSignature().getName();
    logger.info("Start method: " + methodName);
  }

//  @After(value = "com.example.aspect.Pointcuts.allMethods()")
  @After(value = "execution(* com.example.api..*(..))")
  public void logAfterMethod(JoinPoint joinPoint) {
    className = joinPoint.getTarget().getClass().getName();
    logger = LoggerFactory.getLogger(className);
    methodName = joinPoint.getSignature().getName();
    logger.info("Finish method: " + methodName);
  }
}
