package com.assignment.studentmanager.service;

import com.assignment.studentmanager.entity.Clazz;
import com.assignment.studentmanager.entity.Student;
import com.assignment.studentmanager.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService{

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Student> students() {
        return studentRepository.findAll();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student login(Student student) {
        Student studentExist;
        if ((studentExist = studentRepository.findById(student.getEmail()).orElse(null)) != null && studentExist.getPassword().equals(passwordEncoder.encode(student.getPassword()))) {
            return studentExist;
        }
        return null;
    }

    public boolean checkExistEmail(String email){
        Student student;
        return (student = studentRepository.findById(email).orElse(null)) != null && student.getEmail().equals(email);
    }

    public Student findById(String email){
        return studentRepository.findById(email).orElse(null);
    }

}
