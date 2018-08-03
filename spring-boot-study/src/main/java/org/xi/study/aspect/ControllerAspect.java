package org.xi.study.aspect;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.xi.common.constant.OperationConstants;
import org.xi.common.model.ResultVo;
import org.xi.common.utils.AnnotationUtils;
import org.xi.common.utils.LogUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDeclarationException;
import java.lang.reflect.Method;
import java.util.*;

@Component
@Aspect
public class ControllerAspect {

    private final LogUtils logger = LogUtils.build(ControllerAspect.class);

    @Autowired
    HttpServletRequest request;

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

        Signature signature = proceedingJoinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return proceedingJoinPoint.proceed();
        }

        String methodName = "";
        String sessionId = "";
        try {

            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            methodName = request.getRemoteHost() + method.getDeclaringClass().getName() + "." + method.getName();
            sessionId = getSessionId(method, proceedingJoinPoint.getArgs());

            logger.info(methodName, sessionId, "服务开始执行", proceedingJoinPoint.getArgs());

            // StopWatch 计时
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object result = proceedingJoinPoint.proceed();
            stopWatch.stop();

            if (logger.isInfoEnabled()) {
                Map<String, Object> args = new HashMap<>(6);
                args.put("开始执行时间", stopWatch.getStartTime());
                args.put("开始执行时间（用来展示）", new Date(stopWatch.getStartTime()).toString());
                args.put("入参", proceedingJoinPoint.getArgs());
                args.put("出参", result);
                args.put("方法执行时长(ms)", stopWatch.getTime());
                logger.info(methodName, sessionId, "服务执行结束", args);
            }
            return result;
        } catch (ConstraintDeclarationException e) {
            logger.error(methodName, "参数验证失败", e);
            return new ResultVo<>(OperationConstants.VALIDATION_FAILED);
        } catch (Exception e) {
            logger.error(methodName, sessionId, "服务出现异常", e);
        }

        return new ResultVo<>(OperationConstants.SYSTEM_ERROR);
    }


    private String getSessionId(Method method, Object[] args) {

        Object sessionId = null;
        try {
            sessionId = AnnotationUtils.getParam(method, args, "sessionId", RequestParam.class, "value");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (null == sessionId) {
            sessionId = UUID.randomUUID();
        }
        return sessionId.toString();
    }
}
