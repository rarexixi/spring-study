package org.xi.study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class ControllerAspect2 {

    /**
     * 设置标识
     */
    @Pointcut("execution(public * org.xi.study.controller.*.*(..))")
    public void invoke() {
    }

    /**
     * 环绕方法执行，proceedingJoinPoint.proceed()是执行环绕的方法
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("invoke()")
    public Object PlayAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("=======================ControllerAspect2 start");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("=======================ControllerAspect2 end");
        return result;
    }
}
