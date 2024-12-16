package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Student;
import com.adil.unisiteproject.javaproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        // Save student to the database
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        // Fetch student by ID
        return studentRepository.findById(id);
    }

    public Student getStudentByEmail(String email) {
        // Fetch student by email
        return studentRepository.findByEmail(email);
    }

    public void deleteStudent(Long id) {
        // Delete student record
        studentRepository.deleteById(id);
    }

    public Long getGroupIdByStudentId(Long studentId) {
        // Fetch the student using the ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
        // Return the group ID
        return student.getGroup().getId();
    }
    public Long getAuthenticatedStudentId() {
        // Retrieve the ID of the currently authenticated student
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Student authenticatedStudent = studentRepository.findByEmail(authenticatedEmail);
        return authenticatedStudent.getId();
    }
}
