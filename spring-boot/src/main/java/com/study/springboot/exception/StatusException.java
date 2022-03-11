package com.study.springboot.exception;

import org.springframework.http.HttpStatus;

public class StatusException extends RuntimeException {
    protected int status;

    public StatusException(HttpStatus status, String message){
        super(message);
        this.status = status.value();
    }

    public int getStatus(){
        return this.status;
    }
}
