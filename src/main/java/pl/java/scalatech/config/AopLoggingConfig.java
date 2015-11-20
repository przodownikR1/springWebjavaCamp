package pl.java.scalatech.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@Aspect
@EnableAspectJAutoProxy
public class AopLoggingConfig {

    @Before(value = "execution(public * pl.java.scalatech..controller..*.*(..))",
    argNames = "joinPoint")
    public void before(JoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String arguments = Arrays.asList(joinPoint.getArgs()).size() > 0 ? Arrays.asList(joinPoint.getArgs()).toString(): "";
        log.info(className + "." + methodName + "(" + arguments + ")");
    }

    @AfterReturning(
    pointcut = "execution(public * pl.java.scalatech..controller..*.*(..))",
    returning = "returnValue")
    public void afterReturning(Object returnValue) throws Throwable {
        log.info("return " + returnValue);
    }
}