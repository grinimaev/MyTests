package com.testsystem.MyTests.service;

import com.testsystem.MyTests.repository.AnswerRepository;
import com.testsystem.MyTests.repository.QuestionRepository;
import com.testsystem.MyTests.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;



}
