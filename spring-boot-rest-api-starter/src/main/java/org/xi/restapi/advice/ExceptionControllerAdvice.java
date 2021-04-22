package org.xi.restapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.xi.restapi.model.ResponseError;
import org.xi.restapi.model.ResponseModel;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel<Object>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ResponseError> errors = e.getBindingResult().getAllErrors().stream()
                .map(item -> new ResponseError(item.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ResponseModel.fail(errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseModel<Object>> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        List<ResponseError> errors = e.getConstraintViolations().stream()
                .map(item -> new ResponseError(item.getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ResponseModel.fail(errors));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseModel<Object>> ConstraintViolationExceptionHandler(BindException e) {
        List<ResponseError> errors = e.getAllErrors().stream()
                .map(item -> new ResponseError(item.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ResponseModel.fail(errors));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseModel<Object>> ConstraintViolationExceptionHandler(MethodArgumentTypeMismatchException e) {
        List<ResponseError> errors = new ArrayList<>(1);
        errors.add(new ResponseError("参数转换错误"));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ResponseModel.fail(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<Object>> ExceptionHandler(Exception e) {
        List<ResponseError> errors = new ArrayList<>(1);
        errors.add(new ResponseError(e.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseModel.fail(errors));
    }
}