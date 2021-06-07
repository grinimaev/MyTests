package com.testsystem.MyTests.repository;

import com.testsystem.MyTests.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
