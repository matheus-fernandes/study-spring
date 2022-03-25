package com.study.springsecurity.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class InvalidResetAccessException extends ResponseStatusException {
    public InvalidResetAccessException() {
        super(HttpStatus.UNAUTHORIZED, "Not authorized to do the reset password!");
        log.error(this.getMessage());
    }
}
