package com.study.springsecurity.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class UserNotExistsException extends ResponseStatusException {
    public UserNotExistsException() {
        super(HttpStatus.NOT_FOUND, "User Not Found!");
        log.error(this.getMessage());
    }
}
