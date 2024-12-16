package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Teacher;
import com.adil.unisiteproject.javaproject.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher saveTeacher(Teacher teacher) {
        // Add teacher to DB
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }
}