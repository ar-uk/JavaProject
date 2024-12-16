package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Submission;
import com.adil.unisiteproject.javaproject.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission saveSubmission(Submission submission) {
        // Save a student submission
        return submissionRepository.save(submission);
    }

    public Optional<Submission> getSubmissionById(Long id) {
        // Fetch a specific submission
        return submissionRepository.findById(id);
    }

    public List<Submission> getSubmissionsByTaskId(Long taskId) {
        // Get all submissions for a specific task
        return submissionRepository.findByTaskId(taskId);
    }

    public List<Submission> getSubmissionsByStudentId(Long studentId) {
        // Get all submissions made by a specific student
        return submissionRepository.findByStudentId(studentId);
    }

    public void deleteSubmission(Long id) {
        // Delete a submission
        submissionRepository.deleteById(id);
    }
}