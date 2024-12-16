package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Student;
import com.adil.unisiteproject.javaproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        // Save student to the database
        return studentRepository.save(student);
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
}