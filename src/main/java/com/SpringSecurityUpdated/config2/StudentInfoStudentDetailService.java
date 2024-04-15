package com.SpringSecurityUpdated.config2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.SpringSecurityUpdated.model.Student;
import com.SpringSecurityUpdated.repository.StudentRepo;

public class StudentInfoStudentDetailService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentRepo.findBystudentId(username);
        return student.map(StudentInfoDetails::new).orElseThrow(()-> new UsernameNotFoundException("Student Doesn't Exist"));
    }
    
}
