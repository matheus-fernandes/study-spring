package com.study.springsecurity.client.service;

import com.study.springsecurity.client.entity.User;

public interface EmailService {
    void sendRegistrationVerificationLink(User user, String url);
    void sendPasswordResetCode(User user, String code);
}
