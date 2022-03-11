package com.study.springboot.handler;

import com.study.springboot.exception.StatusException;
import com.study.springboot.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus
@ControllerAdvice
public class StatusExceptionHandler {

    @ExceptionHandler(StatusException.class)
    public ResponseEntity<ErrorResponse> handle(StatusException e){

        ErrorResponse errorResponse = new ErrorResponse(
                e.getStatus(), e.getMessage());

        return ResponseEntity.status(
                e.getStatus()).body(errorResponse);
    }
}
