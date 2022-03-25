package com.study.springsecurity.client.controller;

import com.study.springsecurity.client.dummy.PasswordChangeDummy;
import com.study.springsecurity.client.dummy.PasswordResetDummy;
import com.study.springsecurity.client.entity.PasswordReset;
import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.service.EmailService;
import com.study.springsecurity.client.service.PasswordService;
import com.study.springsecurity.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PasswordController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/password/change")
    public void changePassword(@RequestBody PasswordChangeDummy passwordChangeDummy){
        User user = userService.findUserByEmail(passwordChangeDummy.getEmail());

        Optional<PasswordReset> mayReset = passwordService.findResetByUser(user);
        PasswordReset reset = mayReset.orElseGet(() -> passwordService.createResetForUser(user));
        passwordService.registerAttemptIfNotMatchAndError(reset, passwordChangeDummy.getOldPassword());

        userService.changePassword(reset.getUser(), passwordChangeDummy.getNewPassword());
        passwordService.deleteResetById(reset.getId());
    }

    @PostMapping("/password/reset/request")
    public void requestPasswordReset(@RequestParam String email){
        User user = userService.findUserByEmail(email);

        PasswordReset passwordReset = passwordService.createResetForUser(user);
        emailService.sendPasswordResetCode(user, passwordReset.getCode());
    }

    @GetMapping("/password/reset/token")
    public String sendPasswordResetCode(@RequestParam String code, @RequestParam String email){
        User user = userService.findUserByEmail(email);

        PasswordReset passwordReset = passwordService.findResetByUserAndCode(user, code);
        return passwordReset.getToken();
    }

    @PostMapping("/password/reset/finalize")
    public void finalizePasswordReset(@RequestBody PasswordResetDummy finalizePasswordResetDummy){
        passwordService.finalizeReset(finalizePasswordResetDummy);
    }
}
