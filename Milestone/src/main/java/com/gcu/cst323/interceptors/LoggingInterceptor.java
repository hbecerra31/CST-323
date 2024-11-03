package com.gcu.cst323.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class is an Aspect that is used to log method entry and exit points. It
 * logs the class name and method name when a method is entered and exited. It
 * also logs any exceptions that are thrown by the method.
 * 
 * The Aspect is applied to all methods in the com.gcu.cst323 package and its
 * subpackages.
 */
@Aspect
@Component
public class LoggingInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class); // Logger object

	/**
	 * This method logs the entry and exit points of a method. It also logs any
	 * exceptions that are thrown by the method.
	 * 
	 * @param joinPoint - ProceedingJoinPoint object that represents the method
	 *                  being called
	 * @return Object - the result of the method being called
	 * @throws Throwable - any exception that is thrown by the method
	 */
	@Around("execution(* com.gcu.cst323..*(..))")	// Pointcut expression
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {	// ProceedingJoinPoint object
		String className = joinPoint.getSignature().getDeclaringTypeName();	// Class name
		String methodName = joinPoint.getSignature().getName();	// Method name
		logger.info("Entering method: {}.{}", className, methodName);	// Log entry point
		try {
			Object result = joinPoint.proceed(); // Call the method
			logger.info("Exiting method: {}.{}", className, methodName);	// Log exit point
			return result;
		} catch (Exception e) {
			logger.error("Exception in method: {}.{} - {}", className, methodName, e.getMessage());
			throw e;
		}
	}
}
