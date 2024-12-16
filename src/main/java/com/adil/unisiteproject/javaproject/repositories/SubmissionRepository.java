package com.adil.unisiteproject.javaproject.repositories;

import com.adil.unisiteproject.javaproject.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    // Fetch all submissions for a specific task
    List<Submission> findByTaskId(Long taskId);

    // Fetch all submissions made by a specific student
    List<Submission> findByStudentId(Long studentId);
}