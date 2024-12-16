package com.adil.unisiteproject.javaproject.controllers;

import com.adil.unisiteproject.javaproject.models.Student;
import com.adil.unisiteproject.javaproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("student", new Student()); // Form Binding
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(Student student) {
        // Encrypt password before saving
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentService.saveStudent(student);
        return "redirect:/login"; // Redirect to the login page after successful registration.
    }
}