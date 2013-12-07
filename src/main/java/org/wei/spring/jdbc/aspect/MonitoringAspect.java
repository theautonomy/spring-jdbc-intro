package org.wei.spring.jdbc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component("monitoringAspect")
@Profile("aspect")
public class MonitoringAspect {
	
	private static Logger logger = LoggerFactory.getLogger(MonitoringAspect.class);

	@Around("execution(* org.wei.spring.jdbc.*..*Service+.*(..)) && target(service)")
	public Object logServiceAccess(ProceedingJoinPoint joinPoint, Object service) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long totalTime = System.currentTimeMillis() - startTime;
		logger.info("{} {}: invocation time is {} ms ", service.getClass().getSimpleName(), joinPoint.getSignature().getName(), totalTime);
		return result;
	}
	
	
}
