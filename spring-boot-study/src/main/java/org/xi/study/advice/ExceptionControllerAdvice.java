package org.xi.study.advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.xi.study.model.ResponseError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@CrossOrigin
public class ExceptionControllerAdvice {

    // region 参数校验异常

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String error = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String error = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponseError> ValidationExceptionHandler(ValidationException e) {
        String error = e.getMessage();
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseError> BindExceptionHandler(BindException e) {
        String error = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        String error = "参数转换错误";
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    // endregion

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> ExceptionHandler(Exception e) {
        String error = e.getMessage();
        return getError(HttpStatus.INTERNAL_SERVER_ERROR, error);
    }

    private ResponseEntity<ResponseError> getError(HttpStatus httpStatus, String message) {
        ResponseError responseError = new ResponseError();
        responseError.setMessage(message);
        return ResponseEntity.status(httpStatus).body(responseError);
    }
}