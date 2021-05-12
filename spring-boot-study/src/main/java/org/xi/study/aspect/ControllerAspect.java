package org.xi.study.aspect;

import org.xi.common.constant.OperationConstants;
import org.xi.common.model.ResultVo;
import org.xi.common.utils.AnnotationUtils;
import org.xi.common.utils.LogUtils;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = request.getRemoteHost() + method.getDeclaringClass().getName() + "." + method.getName();
        String sessionId = getSessionId(method, proceedingJoinPoint.getArgs());

        logger.info(methodName, sessionId, "服务开始执行", getParameters(proceedingJoinPoint.getArgs()));

        List<String> messages = getErrorMessage(proceedingJoinPoint.getArgs());
        if (!messages.isEmpty()) {
            logger.error(methodName, "参数验证失败", messages);
            return new ResultVo<>(OperationConstants.VALIDATION_FAILED, messages);
        }

        // StopWatch 计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        if (logger.isInfoEnabled()) {
            Map<String, Object> args = new HashMap<>(6);
            args.put("开始执行时间", stopWatch.getStartTime());
            args.put("开始执行时间（用来展示）", new Date(stopWatch.getStartTime()).toString());
            args.put("出参", result);
            args.put("方法执行时长(ms)", stopWatch.getTime());
            logger.info(methodName, sessionId, "服务执行结束", args);
        }
        return result;
    }

    /**
     * 获取非Errors类型参数，否则转json报错
     *
     * @param args
     * @return
     */
    private List<Object> getParameters(Object[] args) {
        List<Object> parameters = new LinkedList<>();
        for (Object arg : args) {
            if (arg instanceof Errors) continue;
            parameters.add(arg);
        }
        return parameters;
    }

    /**
     * 获取拦截的Errors信息
     *
     * @param args
     * @return
     */
    private List<String> getErrorMessage(Object[] args) {
        List<String> messages = new LinkedList<>();
        for (Object arg : args) {
            if (arg instanceof Errors && ((Errors) arg).hasErrors()) {
                for (ObjectError objectError : ((Errors) arg).getAllErrors()) {
                    messages.add(objectError.getDefaultMessage());
                }
            }
        }
        return messages;
    }

    /**
     * 获取session ID
     *
     * @param method
     * @param args
     * @return
     */
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
