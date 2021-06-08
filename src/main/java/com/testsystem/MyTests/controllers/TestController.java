package com.testsystem.MyTests.controllers;

import com.testsystem.MyTests.service.EmailService;
import com.testsystem.MyTests.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    EmailService emailService;

    @GetMapping("/new")
    public String newTest(){
        return "/newTest";
    }

    @PostMapping("/new")
    public String savenewTest(){
        return "/";
    }

}
