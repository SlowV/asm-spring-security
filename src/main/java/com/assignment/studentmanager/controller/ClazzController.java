package com.assignment.studentmanager.controller;

import com.assignment.studentmanager.entity.Clazz;
import com.assignment.studentmanager.entity.Student;
import com.assignment.studentmanager.service.ClazzService;
import com.assignment.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "clazz")
public class ClazzController {
    @Autowired
    ClazzService clazzService;

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/create/seeder")
    public String seeder(){
        if (clazzService.clazzes().size() == 0){
            ArrayList<Clazz> list = new ArrayList<>(Arrays.asList(
                    new Clazz("T1708E"),
                    new Clazz("T1808A"),
                    new Clazz("T1708M")));
            for (Clazz clazz : list) {
                clazzService.save(clazz);
            }
            return "insert success!";
        }
        return "Clazz list not empty!";
    }

    @PostMapping(value = "/change")
    public int changeStudentForClazz(
            @RequestParam(value = "action") String action,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "studentId") String email){
        int clazzId = -1;
        if (action == null || id == null || email == null){
            return HttpStatus.NOT_FOUND.value();
        }
        try {
            clazzId = Integer.parseInt(id);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return HttpStatus.NOT_FOUND.value();
        }

        Clazz clazzExist = clazzService.findById(clazzId);
        Student student = studentService.findById(email);
        if (clazzExist == null || student == null){
            return HttpStatus.NOT_FOUND.value();
        }
        if(action.equals("ADD")){
            clazzExist.addStudent(student);
        }else if (action.equals("REMOVE")){
            clazzExist.removeStudent(student);
        }else{
            return HttpStatus.BAD_REQUEST.value();
        }

        clazzService.save(clazzExist);

        return HttpStatus.OK.value();
    }
}
