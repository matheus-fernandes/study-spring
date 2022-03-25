package com.study.springsecurity.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class PasswordWarningException extends ResponseStatusException {
    public PasswordWarningException() {
        super(HttpStatus.UNAUTHORIZED, "Cannot proceed the operation because of password warning!");
        log.error(this.getMessage());
    }
}
