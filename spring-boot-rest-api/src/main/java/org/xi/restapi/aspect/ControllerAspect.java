package org.xi.restapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class ControllerAspect {

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(public org.springframework.http.ResponseEntity org.xi.restapi.controller.*.*(..))")
    public void invoke() {
    }

    @Around("invoke()")
    public Object PlayAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Signature signature = proceedingJoinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return proceedingJoinPoint.proceed();
        }
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = request.getRemoteHost() + method.getDeclaringClass().getName() + "." + method.getName();
        String path = request.getServletPath();
        return proceedingJoinPoint.proceed();
    }
}
