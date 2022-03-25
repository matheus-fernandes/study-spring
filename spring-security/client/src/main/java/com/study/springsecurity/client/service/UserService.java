package com.study.springsecurity.client.service;

import com.study.springsecurity.client.dummy.UserDummy;
import com.study.springsecurity.client.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService{
    User registerUser(UserDummy dummy);
    void enableUser(User user);
    User findUserByEmail(String email);
    void changePassword(User user, String newPassword);
    boolean matchPassword(String oldPassword, User user);
    void enablePasswordWarning(User user);

    PasswordEncoder getEncoder();
}
