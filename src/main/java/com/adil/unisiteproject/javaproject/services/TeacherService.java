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

    public String getAuthenticatedTeacherEmail() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }

    public List<Group> getGroupsByTeacherId(Long teacherId) {
        return groupRepository.findByTeacherId(teacherId);
    }

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private GroupRepository groupRepository;

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }
}