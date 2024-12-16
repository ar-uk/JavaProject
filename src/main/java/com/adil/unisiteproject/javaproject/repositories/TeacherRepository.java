package com.adil.unisiteproject.javaproject.repositories;

import com.adil.unisiteproject.javaproject.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
}