package org.xi.study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.xi.common.constant.OperationConstants;
import org.xi.common.model.ResultVo;
import org.xi.common.utils.LogUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
@Aspect
public class ValidateAspect {

    private final LogUtils logger = LogUtils.build(ValidateAspect.class);

    @Autowired
    Validator validator;

    /**
     * 设置标识
     */
    @Pointcut("execution(public * org.xi.study.service.impl.*.*(..))")
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
    public Object Test(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodName = "";
        try {
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Parameter[] parameters = method.getParameters();
            Object[] args = proceedingJoinPoint.getArgs();

            List<String> messages = new LinkedList<>();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                Valid valid = parameter.getAnnotation(Valid.class);
                Validated validated = parameter.getAnnotation(Validated.class);
                if (valid == null && validated == null) continue;
                Set<ConstraintViolation<Object>> validateResults = valid != null
                        ? validator.validate(args[i])
                        : validator.validate(args[i], validated.value());
                for (ConstraintViolation<Object> constraintViolation : validateResults) {
                    String message = constraintViolation.getMessage();
                    messages.add(message);
                }
            }
            if (!messages.isEmpty()) {
                return new ResultVo<>(OperationConstants.VALIDATION_FAILED, messages);
            }

            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logger.error(methodName, "服务出现异常", e);
        }

        return new ResultVo<>(OperationConstants.SYSTEM_ERROR);
    }
}
