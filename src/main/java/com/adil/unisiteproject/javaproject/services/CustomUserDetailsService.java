package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Teacher;
import com.adil.unisiteproject.javaproject.models.Student;
import com.adil.unisiteproject.javaproject.repositories.TeacherRepository;
import com.adil.unisiteproject.javaproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Check if user is a teacher
        Teacher teacher = teacherRepository.findByEmail(email);
        if (teacher != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(teacher.getRole());
            return new User(teacher.getEmail(), teacher.getPassword(), Collections.singletonList(authority));
        }

        // Check if user is a student
        Student student = studentRepository.findByEmail(email);
        if (student != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(student.getRole());
            return new User(student.getEmail(), student.getPassword(), Collections.singletonList(authority));
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}