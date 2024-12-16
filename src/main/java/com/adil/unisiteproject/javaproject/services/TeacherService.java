package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Group;
import com.adil.unisiteproject.javaproject.models.Teacher;
import com.adil.unisiteproject.javaproject.repositories.GroupRepository;
import com.adil.unisiteproject.javaproject.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherService {

    // Added methods
    public String getAuthenticatedTeacherEmail() {
        // Logic to fetch the authenticated teacher's email
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }

    public List<Group> getGroupsByTeacherId(Long teacherId) {
        // Logic to fetch groups associated with a teacher ID
        return groupRepository.findByTeacherId(teacherId);
    }

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private GroupRepository groupRepository; // Added repository for groups

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }
}