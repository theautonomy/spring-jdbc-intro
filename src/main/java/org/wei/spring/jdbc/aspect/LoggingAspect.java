package org.wei.spring.jdbc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("loggingAspect")
public class LoggingAspect {
	
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* org.wei.spring.jdbc.*..*Service+.*(..)) && target(service)")
	public void logBeforeEnter( JoinPoint joinPoint, Object service) {
		logger.info("Enter: class - {} method - {} ", service.getClass().getSimpleName(), joinPoint.getSignature().getName());
	}
	
	@After("execution(* org.wei.spring.jdbc.*..*Service+.*(..)) && target(service)")
	public void logAfterEnter(Object service) {
		logger.info("Exit:  {}", service.getClass().getSimpleName());
	}
	
	@Around("execution(* org.wei.spring.jdbc.*..*Service+.*(..)) && target(service)")
	public Object logServiceAccess(ProceedingJoinPoint jp, Object service) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = jp.proceed();
		long totalTime = System.currentTimeMillis() - startTime;
		logger.info("{} {}: invocation time {} ms ", service.getClass().getSimpleName(), jp.getSignature().getName(), totalTime);
		
		return result;
	}
	
}
