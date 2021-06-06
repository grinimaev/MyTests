package com.testsystem.MyTests.repository;

import com.testsystem.MyTests.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String activationCode);
    User findByEmail(String email);
}
