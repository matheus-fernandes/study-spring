package com.study.springsecurity.client.service;

import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.entity.RegistrationVerification;

public interface RegistrationVerificationService {
    RegistrationVerification saveVerificationFor(User user);
    void verifyByToken(String token);
    RegistrationVerification regenerateTokenFor(User user);
}
