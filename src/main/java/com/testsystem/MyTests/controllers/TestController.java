package com.testsystem.MyTests.controllers;

import com.testsystem.MyTests.models.Question;
import com.testsystem.MyTests.models.Test;
import com.testsystem.MyTests.models.User;
import com.testsystem.MyTests.repository.QuestionRepository;
import com.testsystem.MyTests.repository.TestRepository;
import com.testsystem.MyTests.repository.UserRepository;
import com.testsystem.MyTests.service.EmailService;
import com.testsystem.MyTests.service.TestService;
import com.testsystem.MyTests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    EmailService emailService;

   @Autowired
    TestRepository testRepository;

   @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/new")
    public String newTest(){
        return "newTest";
    }

    @PostMapping("/new")
    public String addNewTest(@AuthenticationPrincipal UserDetails u, @RequestParam String name, @RequestParam String description){
      Long testId=testService.addNewTest(u.getUsername(), name, description);
        return "redirect:/test/edit/"+testId;
    }

    @GetMapping("/edit/{id}")
    public String newTest(@PathVariable Long id, Model model){
        model.addAttribute("test", testRepository.findById(id).get());
        return "editTest";
    }

    @GetMapping("/edit/newQuestion/{id}")
    public String getNewQuest(@PathVariable Long id, Model model){
        model.addAttribute("testid", id);
        return "newQuestion";
    }
    @PostMapping("/edit/newQuestion/{id}")
    public String postNewQuest(@PathVariable Long id, @RequestParam("answer") List<String> answers, @RequestParam String question, @RequestParam Long trueAns){
        testService.addQuestion(id,question,answers,trueAns);
        return "newQuestion";
    }
    @GetMapping("/edit/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id)
    {
        Question quest = questionRepository.findById(id).get();
        Test test = quest.getTest();
        testService.deleteQuest(id);
        return "redirect:/test/edit/"+test.getId();
    }
}
