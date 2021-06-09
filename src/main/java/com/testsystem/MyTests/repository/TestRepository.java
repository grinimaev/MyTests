package com.testsystem.MyTests.repository;

import com.testsystem.MyTests.models.Role;
import com.testsystem.MyTests.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TestRepository extends JpaRepository<Test, Long> {
    @Modifying
    @Transactional
    @Query(value="delete from user_test where test_id = :testId ", nativeQuery = true )
    int deleteForeignKey(@Param("testId")Long testId);

}
