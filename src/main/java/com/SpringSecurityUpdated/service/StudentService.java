package com.SpringSecurityUpdated.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.SpringSecurityUpdated.repository.StudentRepo;
import com.SpringSecurityUpdated.model.Student;
import com.SpringSecurityUpdated.pojo.PasswordDto;
import com.SpringSecurityUpdated.utils.UtilMethods;

@Component
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    private UtilMethods utilMethods;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudents() {

        List<Student> students = studentRepo.findAll();
        return students;
    }

    public Student getLoggedInStudent() {

        Student student = studentRepo.findBystudentId(utilMethods.getLoggedInUserDetails().getUsername()).get();
        return student;
    }

    public Student getStudent(String studentId) {

        Student student = studentRepo.findBystudentId(studentId).get();
        return student;
    }

    public Student saveStudent(Student student) {

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        Student fetchedStudent = studentRepo.save(student);
        return fetchedStudent;
    }

    public Student updatePassword(PasswordDto passwordDto) {

        Student result = null;
        try {
            String currentPassword = passwordDto.getCurrentPassword();
            String newPassword = passwordDto.getNewPassword();

            // Fetching the Logged In User Details
            String username = utilMethods.getLoggedInUserDetails().getUsername();
            String oldPassword = utilMethods.getLoggedInUserDetails().getPassword();

            // Handling the case where no username not matched or found
            Student student = studentRepo.findBystudentId(username).get();

            if (student == null)
                throw new UsernameNotFoundException("Student not found");

            System.err.println(passwordEncoder.matches(currentPassword, oldPassword));

            if (passwordEncoder.matches(currentPassword, oldPassword)) {
                student.setPassword(passwordEncoder.encode(newPassword));
                result = studentRepo.save(student);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public boolean deleteStudent(String studentId) {
        studentRepo.deleteByStudentId(studentId);
        return true;
    }

}
