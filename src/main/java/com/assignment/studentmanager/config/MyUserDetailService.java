package com.assignment.studentmanager.config;

import com.assignment.studentmanager.entity.Student;
import com.assignment.studentmanager.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findById(email).orElse(null);
        if (student == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .roles("USER")
                .build();
    }
}
