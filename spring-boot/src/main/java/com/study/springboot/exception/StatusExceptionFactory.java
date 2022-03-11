package com.study.springboot.exception;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.*;


public class StatusExceptionFactory {
    private String message;

    public StatusExceptionFactory(String message){
        this.message = message;
    }

    public StatusException notFound(){
        return new StatusException(NOT_FOUND, message);
    }

    public StatusException noContent(){
        return new StatusException(NO_CONTENT, message);
    }
}
