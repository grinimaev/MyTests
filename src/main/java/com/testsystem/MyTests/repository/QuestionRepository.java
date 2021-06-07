package com.testsystem.MyTests.repository;

import com.testsystem.MyTests.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
