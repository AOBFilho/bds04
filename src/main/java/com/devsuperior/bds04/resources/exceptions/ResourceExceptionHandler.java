package com.devsuperior.bds04.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> validationErrorResponseEntity(ConstraintViolationException e, HttpServletRequest request){
        var error = new ValidationError();
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(Instant.now());
        error.getErrors().clear();
        e.getConstraintViolations().stream().forEach(x -> error.addMessage(x.getPropertyPath().toString(),x.getMessage()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        var error = new ValidationError();
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(Instant.now());
        error.getErrors().clear();
        e.getBindingResult().getFieldErrors().stream().forEach(x -> error.addMessage(x.getField(),x.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

}
