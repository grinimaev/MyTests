package com.testsystem.MyTests.controllers;

import com.testsystem.MyTests.models.User;
import com.testsystem.MyTests.repository.UserRepository;
import com.testsystem.MyTests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "MyTests");
        return "home";
    }
    @GetMapping("/register")
    public String registerGet(Model model) {
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@RequestParam String email, @RequestParam String username, @RequestParam String password,@RequestParam String role, Model model){
     int errorCheck = userService.registerUser(username,password,email,role);
     switch (errorCheck){
         case(-1): model.addAttribute("error", "E-mail занят!");
                break;
         case(-2): model.addAttribute("error", "Имя пользовтеля занято!");
             break;
         default:
             model.addAttribute("error", "Успешная регистрация! Письмо с инструкцией активации отправлено!");
     }
     return("register");
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
    @GetMapping("/recoverPass")
    public String recoverPassGet() {
        return "recoverPass";
    }
    @PostMapping("/recoverPass")
    public String recoverPassGet(@RequestParam String email, Model model) {
        if(userService.recoverPassword(email)) model.addAttribute("rec", "Ссылка для смены пароля отпралена на e-mail");
        else model.addAttribute("rec", "Аккаунт не существует");
        return "recoverPass";
    }
    @GetMapping("recoverPassword/{acrivationCode}")
    public String recoverGet(@PathVariable String acrivationCode, Model model) {
        if(!userService.findByAcrivationCode(acrivationCode)) model.addAttribute("error", "Ошибка восстановления");
        model.addAttribute("code", acrivationCode );
        return "recoverPassword";
    }
    @PostMapping("/recoverPassword/{acrivationCode}")
    public String recoverPost(@PathVariable String acrivationCode, @RequestParam String password, Model model){
        userService.recPassword(acrivationCode,password);
        model.addAttribute("recover", "Пароль восстановлен");
        return("login");
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails u, Model model) {
        User user = userRepository.findByUsername(u.getUsername());
        model.addAttribute("user", user);
        return ("profile");
    }

}