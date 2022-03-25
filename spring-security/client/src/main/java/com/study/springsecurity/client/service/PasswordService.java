package com.study.springsecurity.client.service;

import com.study.springsecurity.client.dummy.PasswordChangeDummy;
import com.study.springsecurity.client.dummy.PasswordResetDummy;
import com.study.springsecurity.client.entity.PasswordReset;
import com.study.springsecurity.client.entity.User;

import java.util.Optional;

public interface PasswordService {
    PasswordReset createResetForUser(User user);
    PasswordReset findResetByUserAndCode(User user, String code);

    Optional<PasswordReset> findResetByUser(User user);
    void finalizeReset(PasswordResetDummy passwordResetDummy);
    void registerAttemptIfNotMatchAndError(PasswordReset reset, String oldPassword);
    void deleteResetById(Long id);
}
