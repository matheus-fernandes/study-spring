package com.study.springsecurity.client.controller;

import com.study.springsecurity.client.dummy.UserDummy;
import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.entity.RegistrationVerification;
import com.study.springsecurity.client.service.EmailService;
import com.study.springsecurity.client.service.UserService;
import com.study.springsecurity.client.service.RegistrationVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegistrationVerificationService registrationVerificationService;

    @PostMapping("/registration/request")
    public void requestRegistration(@RequestBody UserDummy dummy, final HttpServletRequest request){
        User user = userService.registerUser(dummy);
        RegistrationVerification verification = registrationVerificationService.saveVerificationFor(user);
        String url = buildUrl(verification.getToken(), request);

        emailService.sendRegistrationVerificationLink(user, url);
    }

    private static final String TOKEN_REQ_PARAM = "token";
    private static final String VERIFICATION_PATH = "/registration/verification/";

    @GetMapping(VERIFICATION_PATH)
    public String verifyRegistration(@RequestParam(TOKEN_REQ_PARAM) String token){
        registrationVerificationService.verifyByToken(token);

        return "SUCCESSFULLY VALIDATED!";
    }

    @PostMapping("/registration/retry")
    public void retryVerification(@RequestParam("email") String email, HttpServletRequest request){
        User user = userService.findUserByEmail(email);
        RegistrationVerification verification = registrationVerificationService.regenerateTokenFor(user);

        String url = buildUrl(verification.getToken(), request);
        emailService.sendRegistrationVerificationLink(user, url);
    }

    static String buildUrl(String token, HttpServletRequest request){
        return "http://" + request.getServerName() +
                ":" + request.getServerPort() +
                request.getContextPath() +
                VERIFICATION_PATH + "?" +
                TOKEN_REQ_PARAM + "=" + token;
    }
}
