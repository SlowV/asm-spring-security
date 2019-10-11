package com.assignment.studentmanager.controller;

import com.assignment.studentmanager.entity.Clazz;
import com.assignment.studentmanager.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "clazz")
public class ClazzController {
    @Autowired
    ClazzService clazzService;

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
}
