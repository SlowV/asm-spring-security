package com.assignment.studentmanager.repo;

import com.assignment.studentmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StudentRepository extends JpaRepository<Student, String> {
//    Student findByEmail(String email);
}
