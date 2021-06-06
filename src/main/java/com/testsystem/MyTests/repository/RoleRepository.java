package com.testsystem.MyTests.repository;

import com.testsystem.MyTests.models.Role;
import com.testsystem.MyTests.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}