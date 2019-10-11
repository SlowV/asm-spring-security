package com.assignment.studentmanager.controller;

import com.assignment.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "auth")
public class AuthController {
    @RequestMapping(value = {"", "/login"})
    public String login() {
//        System.out.println(studentService.findById("quocviet.hn98@gmail.com").toString());
        return "login";
    }
}
