package com.SpringSecurityUpdated.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringSecurityUpdated.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
   
    // @Query(value = "select * from student_details where student_id = ?1", nativeQuery = true)
    Optional<Student> findBystudentId(String studentId);

    Optional<Student> deleteByStudentId(String studentId);

}

