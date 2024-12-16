package com.adil.unisiteproject.javaproject.repositories;

import com.adil.unisiteproject.javaproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email); // Fetch a student by email for login or dashboard
}