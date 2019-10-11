package com.assignment.studentmanager.repo;

import com.assignment.studentmanager.entity.Clazz;
import com.assignment.studentmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
    
}
