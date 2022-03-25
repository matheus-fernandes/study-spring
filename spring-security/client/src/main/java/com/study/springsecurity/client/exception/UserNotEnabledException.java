package com.study.springsecurity.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class UserNotEnabledException extends ResponseStatusException {
    public UserNotEnabledException() {
        super(HttpStatus.UNAUTHORIZED, "The user was not enabled yet!");
        log.error(this.getMessage());
    }
}
