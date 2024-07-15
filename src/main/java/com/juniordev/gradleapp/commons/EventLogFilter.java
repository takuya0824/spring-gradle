package com.juniordev.gradleapp.commons;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class EventLogFilter {

  @Before("execution(* com.juniordev.gradleapp..*Controller.*(..))")
  public void beforeLog(JoinPoint jointPoint) {
    log.info(String.format("%s START", jointPoint.toShortString()));
  }


  @After("execution(* com.juniordev.gradleapp..*Controller.*(..))")
  public void afterLog(JoinPoint jointPoint) {
    log.info(String.format("%s END", jointPoint.toShortString()));
  }
}
