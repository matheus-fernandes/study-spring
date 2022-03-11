package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HomeController {
    @Value("${spring.welcome.message}")
    private String helloMessage;

    @RequestMapping(path = "/", method = GET)
    public String get(){
        return "Hello to my SPRING BOOT studies!";
    }

    @GetMapping("/hello")
    public String getHelloWorld(){
        return helloMessage;
    }
}
