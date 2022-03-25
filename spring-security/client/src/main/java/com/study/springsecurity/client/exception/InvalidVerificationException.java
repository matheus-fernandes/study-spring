package com.study.springsecurity.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class InvalidVerificationException extends ResponseStatusException {
    public InvalidVerificationException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid Verification!");
        log.error(this.getMessage());
    }
}
