package com.SpringSecurityUpdated.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SpringSecurityUpdated.model.Student;
import com.SpringSecurityUpdated.pojo.PasswordDto;
import com.SpringSecurityUpdated.service.StudentService;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String message() {
        return "The Api is working fine";
    }

    @GetMapping("/all/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        if (students.size() > 0 && students != null)
            return ResponseEntity.ok(students);
        return ResponseEntity.status(404).body("Students not found");
    }

    @GetMapping("/loggedIn/student")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Object> getLoggedInStudent() {
        Student student = studentService.getLoggedInStudent();

        if (student != null)
            return ResponseEntity.ok(student);
        return ResponseEntity.status(404).body("Student not found");
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getStudent(@PathVariable("studentId") String studentId) {
        Student student = studentService.getStudent(studentId);

        if (student != null)
            return ResponseEntity.ok(student);
        return ResponseEntity.status(404).body("Student not found");
    }

    @PostMapping("/save/student")
    // I used the hasAuthority in the config file (for learning purpose) 
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        Student result = studentService.saveStudent(student);
        if (result.getId() > 0 & result != null)
            return ResponseEntity.ok("Student saved successfully");
        return ResponseEntity.status(400).body("Student data not saved");
    }

    @PutMapping("/update/password")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> updatePassword(@RequestBody PasswordDto passwordDto) {

        Student student = studentService.updatePassword(passwordDto);
        if (student != null)
            return ResponseEntity.ok("Password Updated for " + student.getStudentId());
        return ResponseEntity.status(400).body("Password not matched");
    }

    @DeleteMapping("/student/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") String studentId) {

        boolean student = studentService.deleteStudent(studentId);
        if (student)
            return ResponseEntity.ok("Student deleted successfully");
        return ResponseEntity.status(404).body("StudentId didn't found");
    }

}
