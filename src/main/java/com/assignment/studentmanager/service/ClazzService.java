package com.assignment.studentmanager.service;

import com.assignment.studentmanager.entity.Clazz;
import com.assignment.studentmanager.repo.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService {
    @Autowired
    ClazzRepository clazzRepository;

    public List<Clazz> clazzes(){
        return clazzRepository.findAll();
    }

    public void save(Clazz clazz){
        clazzRepository.save(clazz);
    }

    public Clazz findById(int id){
        return clazzRepository.findById(id).orElse(null);
    }
}
