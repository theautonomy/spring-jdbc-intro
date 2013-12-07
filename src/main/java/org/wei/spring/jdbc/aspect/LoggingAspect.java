package org.wei.spring.jdbc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component("loggingAspect")
@Profile("aspect")
public class LoggingAspect {
	
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	
	@Before("execution(* org.wei.spring.jdbc.*..*Service +.*(..)) && target(service)")
	public void logBeforeEnter(JoinPoint joinPoint, Object service) {
		logger.info("Enter: class {}, method {} ", service.getClass().getSimpleName(), joinPoint.getSignature().getName());
	}
	
	@After("execution(* org.wei.spring.jdbc.*..*Service +.*(..)) && target(service)")
	public void logAfterEnter(JoinPoint joinPoint, Object service) {
		logger.info("Exit: class {}, method {} ", service.getClass().getSimpleName(), joinPoint.getSignature().getName());
	}
	
	/* This works with xml configuratio but not java configuration */
	/*
	@After("execution(* org.wei.spring.jdbc.*..* +.*(..)) && target(service)")
	public void logAfterEnter(Object service) {
		logger.info("Exit:  {}", service.getClass().getSimpleName());
	}
	*/
	
}
