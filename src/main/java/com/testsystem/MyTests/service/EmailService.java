package com.testsystem.MyTests.service;

import java.util.Properties;

import com.testsystem.MyTests.models.Test;
import com.testsystem.MyTests.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void send(String mailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }

    public void sendActivationUrl(User u) {
        send(u.getEmail(), "Активация аккаунта", "Активация для пользователя " + u.getUsername() + ". Для активации аккаунта перейдите по ссылке http://213.147.196.194:8080/confirmRegistration/" + u.getActivationCode());
    }
    public void sendRecoverUrl(User u) {
        send(u.getEmail(), "Восстановление пароля", "Восстановление пароля для " + u.getUsername() + ". Для восстановления аккаунта перейдите по ссылке http://213.147.196.194:8080/recoverPassword/" + u.getActivationCode());
    }

    public void sendTestResult(String username, Test test, int result, int count) {
        send(test.getUser().getEmail(), "Кто-то прошёл ваш тест!", "Здравствуйте! Пользователь " + username + " прошел ваш тест! Его результат " + result+"/"+count);
   }

}
