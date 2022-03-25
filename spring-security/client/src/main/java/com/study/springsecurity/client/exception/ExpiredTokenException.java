package com.study.springsecurity.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class ExpiredTokenException extends ResponseStatusException {
    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED, "The token was expired!");
        log.error(this.getMessage());
    }
}
