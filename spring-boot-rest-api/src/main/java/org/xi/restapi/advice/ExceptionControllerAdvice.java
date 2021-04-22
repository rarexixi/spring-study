package org.xi.restapi.advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.xi.restapi.exception.DataNotFoundException;
import org.xi.restapi.model.ResponseError;
import org.xi.restapi.model.ResponseModel;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String error = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String error = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";"));
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseError> ConstraintViolationExceptionHandler(BindException e) {
        String error = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> ConstraintViolationExceptionHandler(MethodArgumentTypeMismatchException e) {
        String error = "参数转换错误";
        return getError(HttpStatus.UNPROCESSABLE_ENTITY, error);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseError> ExceptionHandler(DataNotFoundException e) {
        String error = e.getMessage();
        return getError(HttpStatus.NOT_FOUND, error);
    }

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