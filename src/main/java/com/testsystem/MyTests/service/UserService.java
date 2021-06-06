package com.testsystem.MyTests.service;

import com.testsystem.MyTests.models.Role;
import com.testsystem.MyTests.models.User;
import com.testsystem.MyTests.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails u =(UserDetails)  userRepository.findByUsername(username);
        if(u == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return u;
        }
    }
    public void registerUser(String username, String password, String email){
        User u = new User(username, bCryptPasswordEncoder.encode(password), email);
        u.setActivated(false);
        u.setActivationCode(UUID.randomUUID().toString());
        emailService.sendActivationUrl(u);
        u.setRole(Collections.singleton(new Role()));
        userRepository.save(u);
    }
    public boolean confirmRegistration(String activationCode){
       User u = userRepository.findByActivationCode(activationCode);
       if(u!=null) {
           u.setActivated(true);//test git
           u.setActivationCode(null);
           userRepository.save(u);
           return true;
       }
       else{
           return false;
       }
    }

}
