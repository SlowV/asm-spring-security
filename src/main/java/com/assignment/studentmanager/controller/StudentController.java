package com.assignment.studentmanager.controller;

import com.assignment.studentmanager.entity.Clazz;
import com.assignment.studentmanager.entity.Student;
import com.assignment.studentmanager.service.ClazzService;
import com.assignment.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = "students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    ClazzService clazzService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        System.out.println(name);
        model.addAttribute("username", name);
        model.addAttribute("students", studentService.students());
        model.addAttribute("clazzes", clazzService.clazzes());
        return "index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("clazzes", clazzService.clazzes());
        return "create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(Model model, Student student, BindingResult bindingResult, @RequestParam("clazzId") String clazzId) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("student", student);
//            model.addAttribute("clazzes", clazzService.clazzes());
//            return "create";
//        }
        Clazz clazz = clazzService.findById(Integer.parseInt(clazzId));
        if (!studentService.checkExistEmail(student.getEmail())) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            clazz.addStudent(student);
            clazzService.save(clazz);
        }
        return "redirect:/students";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public String profile(Model model, @Param("email") String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getName().equals(email)) {
            Student student = studentService.findById(email);
            if (student != null){
                model.addAttribute("student", student);
                model.addAttribute("clazzes", clazzService.clazzes());
                return "profile";
            }
        }
        return "redirect:/students";
    }
}

