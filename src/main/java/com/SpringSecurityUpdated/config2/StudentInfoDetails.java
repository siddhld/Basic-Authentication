package com.SpringSecurityUpdated.config2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SpringSecurityUpdated.model.Student;

public class StudentInfoDetails implements UserDetails {

    private String studentId;
    private String password;
    private List<GrantedAuthority> roles;

    public StudentInfoDetails(Student student) {
        this.studentId = student.getStudentId();
        this.password = student.getPassword();
        // this.roles =
        // Arrays.stream(student.getRoles().stream().toArray(String[]::new))
        // .map(SimpleGrantedAuthority::new)
        // .collect(Collectors.toList());

        roles = new ArrayList<>();
        for (String role : student.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.studentId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
