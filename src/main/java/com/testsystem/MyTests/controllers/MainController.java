package com.testsystem.MyTests.controllers;

import com.testsystem.MyTests.models.User;
import com.testsystem.MyTests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "MyTests");
        return "home";
    }
    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@RequestParam String email, @RequestParam String username, @RequestParam String password){
     userService.registerUser(username,password,email);
     return("login");
    }
    @GetMapping("/confirmRegistration/{activationCode}")
    public String confirmRegistration( @PathVariable String activationCode, Model model) {
        if(userService.confirmRegistration(activationCode)){
            model.addAttribute("msg", "Аккаунт успешно активирован");
        }
        else
        {
            model.addAttribute("msg", "Ошибка активации");
        }
        return "register";
    }

}