package com.study.springsecurity.client.serviceimpl;

import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendRegistrationVerificationLink(User user, String url) {
        log.info("{}, click the link to verify your account: {}", user.getFirstName() , url);
    }

    @Override
    public void sendPasswordResetCode(User user, String code) {
        log.info("{}, your verification token is: {}", user.getFirstName(), code);
    }
}
