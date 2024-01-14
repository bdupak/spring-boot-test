package com.example.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
  @Pointcut("execution(* com.example.api.*.*.*(..))")
  public void apiMethods() {}

  @Pointcut("execution(* com.example.database..*(..))")
  public void databaseMethods() {}

  @Pointcut("apiMethods() && databaseMethods()")
  public void allMethods() {}
}
