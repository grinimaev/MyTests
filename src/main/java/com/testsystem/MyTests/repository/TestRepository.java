package com.testsystem.MyTests.repository;

import com.testsystem.MyTests.models.Role;
import com.testsystem.MyTests.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
